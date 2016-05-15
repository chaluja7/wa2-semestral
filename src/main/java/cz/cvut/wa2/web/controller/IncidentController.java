package cz.cvut.wa2.web.controller;

import cz.cvut.wa2.entity.*;
import cz.cvut.wa2.service.CommentService;
import cz.cvut.wa2.service.IncidentAddressService;
import cz.cvut.wa2.service.IncidentService;
import cz.cvut.wa2.service.MessageService;
import cz.cvut.wa2.service.googleMaps.GoogleMapsAddressProvider;
import cz.cvut.wa2.utils.WA2DateTimeUtils;
import cz.cvut.wa2.web.controller.exception.BadRequestException;
import cz.cvut.wa2.web.controller.exception.ResourceNotFoundException;
import cz.cvut.wa2.web.interceptor.CheckAccess;
import cz.cvut.wa2.web.wrapper.request.NewIncidentWrapper;
import cz.cvut.wa2.web.wrapper.request.NewMessageWrapper;
import cz.cvut.wa2.web.wrapper.request.UpdateStateWrapper;
import cz.cvut.wa2.web.wrapper.response.ComplexIncidentWrapper;
import cz.cvut.wa2.web.wrapper.response.IncidentAddressWrapper;
import cz.cvut.wa2.web.wrapper.response.MessageWrapper;
import cz.cvut.wa2.web.wrapper.response.SimpleIncidentWrapper;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Incident controller.
 *
 * @author jakubchalupa
 * @since 14.05.16
 */
@RestController
public class IncidentController extends AbstractController {

    @Autowired
    protected IncidentService incidentService;

    @Autowired
    protected MessageService messageService;

    @Autowired
    protected CommentService commentService;

    @Autowired
    protected IncidentAddressService incidentAddressService;

    @Autowired
    protected GoogleMapsAddressProvider googleMapsAddressProvider;

    @RequestMapping(value = "/incidents", method = RequestMethod.GET)
    public List<SimpleIncidentWrapper> getIncidents(@RequestParam(required = false, name = "all") Boolean all) {
        List<SimpleIncidentWrapper> incidentWrappers = new ArrayList<>();

        List<Incident> incidents;
        if(Boolean.TRUE.equals(all)) {
            incidents = incidentService.findAll();
        } else {
            incidents = incidentService.findAll(IncidentState.NEW, IncidentState.IN_PROGRESS);
        }

        for(Incident incident : incidents) {
           incidentWrappers.add(getSimpleIncidentWrapperFromIncident(incident));
        }

        return incidentWrappers;
    }

    @RequestMapping(value = "/incidents/{incidentId}", method = RequestMethod.GET)
    public ComplexIncidentWrapper getIncident(@PathVariable Long incidentId) {
        ComplexIncidentWrapper incidentWrapper = getComplexIncidentWrapperFromIncident(incidentService.findByIdLazyLoaded(incidentId));

        if(incidentWrapper == null) {
            throw new ResourceNotFoundException();
        }

        return incidentWrapper;
    }

    @RequestMapping(value = "/incidents", method = RequestMethod.POST)
    public ResponseEntity<String> doCreateIncident(@RequestBody NewIncidentWrapper incidentWrapper) {
        if(incidentWrapper == null) {
            throw new BadRequestException();
        }

        Incident incident = new Incident();
        incident.setTitle(incidentWrapper.getTitle());
        incident.setDescription(incidentWrapper.getDescription());
        incident.setLatitude(incidentWrapper.getLat());
        incident.setLongitude(incidentWrapper.getLon());

        //TODO tohle bude delat worker!
//        try {
//            String addressFromGPS = googleMapsAddressProvider.getAddressFromGPS(incidentWrapper.getLat(), incidentWrapper.getLon());
//            incident.setAddress(addressFromGPS);
//        } catch (BadGPSException e) {
//            throw new BadRequestException();
//        }

        try {
            incidentService.persist(incident);
        } catch (ConstraintViolationException e) {
            throw new BadRequestException();
        }

        return getResponseCreated("/incidents/" + incident.getId());
    }

    @CheckAccess(Role.Type.ADMIN)
    @RequestMapping(value = "/incidents/{incidentId}", method = RequestMethod.PATCH)
    public ResponseEntity<String> updateState(@PathVariable Long incidentId, @RequestBody UpdateStateWrapper updateStateWrapper) {
        findIncidentByIdOrThrowResourceNotFound(incidentId);

        if(updateStateWrapper == null || updateStateWrapper.getState() == null) {
            throw new BadRequestException();
        }

        IncidentState incidentState = IncidentState.fromStringCode(updateStateWrapper.getState());
        if(incidentState == null) {
            throw new BadRequestException();
        }

        incidentService.updateState(incidentId, incidentState);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/incidents/{incidentId}/messages", method = RequestMethod.GET)
    public List<MessageWrapper> getIncidentMessages(@PathVariable Long incidentId) {
        findIncidentByIdOrThrowResourceNotFound(incidentId);

        List<MessageWrapper> messageWrappers = new ArrayList<>();

        for(Message message : messageService.findByIncidentId(incidentId)) {
            messageWrappers.add(getMessageWrapperFromMessage(message));
        }

        return messageWrappers;
    }

    @CheckAccess(Role.Type.USER)
    @RequestMapping(value = "/incidents/{incidentId}/messages", method = RequestMethod.POST)
    public ResponseEntity<String> doCreateIncidentMessage(@PathVariable Long incidentId, @RequestBody NewMessageWrapper messageWrapper, HttpServletRequest httpServletRequest) {
        Incident incident = findIncidentByIdOrThrowResourceNotFound(incidentId);
        Person person = getCurrentPersonOrThrowUnauthorized(httpServletRequest);

        Message message = new Message();
        message.setAuthor(person);
        message.setIncident(incident);
        message.setText(messageWrapper.getText());

        try {
            messageService.persist(message);
        } catch (ConstraintViolationException e) {
            throw new BadRequestException();
        }

        return getResponseCreated("/incidents/" + incidentId + "/messages/" + message.getId());
    }

    @RequestMapping(value = "/incidents/{incidentId}/comments", method = RequestMethod.GET)
    public List<MessageWrapper> getIncidentComments(@PathVariable Long incidentId) {
        findIncidentByIdOrThrowResourceNotFound(incidentId);

        List<MessageWrapper> commentWrappers = new ArrayList<>();

        for(Comment comment : commentService.findByIncidentId(incidentId)) {
            commentWrappers.add(getCommentWrapperFromComment(comment));
        }

        return commentWrappers;
    }

    @CheckAccess(Role.Type.USER)
    @RequestMapping(value = "/incidents/{incidentId}/comments", method = RequestMethod.POST)
    public ResponseEntity<String> doCreateIncidentComment(@PathVariable Long incidentId, @RequestBody NewMessageWrapper commentWrapper, HttpServletRequest httpServletRequest) {
        Incident incident = findIncidentByIdOrThrowResourceNotFound(incidentId);
        Person person = getCurrentPersonOrThrowUnauthorized(httpServletRequest);

        Comment comment = new Comment();
        comment.setAuthor(person);
        comment.setIncident(incident);
        comment.setText(commentWrapper.getText());

        try {
            commentService.persist(comment);
        } catch (ConstraintViolationException e) {
            throw new BadRequestException();
        }

        return getResponseCreated("/incidents/" + incidentId + "/comments/" + comment.getId());
    }

    @RequestMapping(value = "/incidents/{incidentId}/addresses", method = RequestMethod.GET)
    public ResponseEntity<List<IncidentAddressWrapper>> getIncidentRegion(@PathVariable Long incidentId) {
        Incident incident = findIncidentByIdOrThrowResourceNotFound(incidentId);

        List<IncidentAddressWrapper> wrappers = new ArrayList<>();
        final HttpStatus httpStatus;
        if(incident.getAddress() == null) {
            //jeste nemam adresu spocitanou workerem, takze vratim hlavicku, ze to jeste neni :)
            httpStatus = HttpStatus.ACCEPTED;
        } else {
            //uz mam adresy spocitane
            httpStatus = HttpStatus.OK;

            for(IncidentAddress incidentAddress : incidentAddressService.findByIncidentId(incidentId)) {
                IncidentAddressWrapper incidentAddressWrapper = new IncidentAddressWrapper();
                incidentAddressWrapper.setAddress(incidentAddress.getAddress());
                wrappers.add(incidentAddressWrapper);
            }
        }

        return new ResponseEntity<>(wrappers, httpStatus);
    }

    private Incident findIncidentByIdOrThrowResourceNotFound(Long incidentId) {
        Incident incident = incidentService.find(incidentId);
        if(incident == null) {
            throw new ResourceNotFoundException();
        }

        return incident;
    }

    private SimpleIncidentWrapper getSimpleIncidentWrapperFromIncident(Incident incident) {
        if(incident == null) return null;

        SimpleIncidentWrapper incidentWrapper = new SimpleIncidentWrapper();
        fillIncidentWrapper(incident, incidentWrapper);

        return incidentWrapper;
    }

    private ComplexIncidentWrapper getComplexIncidentWrapperFromIncident(Incident incident) {
        if(incident == null) return null;

        ComplexIncidentWrapper incidentWrapper = new ComplexIncidentWrapper();
        fillIncidentWrapper(incident, incidentWrapper);

        incidentWrapper.setAddress(incident.getAddress());
        incidentWrapper.setDescription(incident.getDescription());

        List<MessageWrapper> messages = new ArrayList<>();
        List<MessageWrapper> comments = new ArrayList<>();

        if(incident.getMessages() != null) {
            for(Message message : incident.getMessages()) {
                messages.add(getMessageWrapperFromMessage(message));
            }
        }

        if(incident.getComments() != null) {
            for(Comment comment : incident.getComments()) {
                comments.add(getCommentWrapperFromComment(comment));
            }
        }

        incidentWrapper.setMessages(messages);
        incidentWrapper.setComments(comments);

        return incidentWrapper;
    }

    /**
     * will copy properties from incident to incident model
     * @param incident incident
     * @param incidentWrapper incident model
     */
    private void fillIncidentWrapper(Incident incident, SimpleIncidentWrapper incidentWrapper) {
        incidentWrapper.setId(incident.getId());
        incidentWrapper.setTitle(incident.getTitle());
        incidentWrapper.setTimeOfCreation(incident.getInsertedTime().toString(WA2DateTimeUtils.DATE_TIME_PATTERN));
        incidentWrapper.setState(incident.getState().name());
        incidentWrapper.setLat(incident.getLatitude());
        incidentWrapper.setLon(incident.getLongitude());
    }

    private MessageWrapper getMessageWrapperFromMessage(Message message) {
        MessageWrapper messageWrapper = new MessageWrapper();
        messageWrapper.setText(message.getText());
        messageWrapper.setTimeOfCreation(message.getInsertedTime().toString(WA2DateTimeUtils.DATE_TIME_PATTERN));
        if(message.getAuthor() != null) {
            messageWrapper.setAuthor(message.getAuthor().getWholeName());
        }

        return messageWrapper;
    }

    private MessageWrapper getCommentWrapperFromComment(Comment comment) {
        MessageWrapper messageWrapper = new MessageWrapper();
        messageWrapper.setText(comment.getText());
        messageWrapper.setTimeOfCreation(comment.getInsertedTime().toString(WA2DateTimeUtils.DATE_TIME_PATTERN));
        if(comment.getAuthor() != null) {
            messageWrapper.setAuthor(comment.getAuthor().getWholeName());
        }

        return messageWrapper;
    }

}
