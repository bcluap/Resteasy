package org.jboss.resteasy.star.messaging.topic;

import org.jboss.resteasy.star.messaging.Constants;
import org.jboss.resteasy.star.messaging.LinkHeaderSupport;
import org.jboss.resteasy.star.messaging.queue.ConsumersResource;
import org.jboss.resteasy.star.messaging.queue.PostMessage;
import org.jboss.resteasy.star.messaging.queue.QueueConsumer;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
public class TopicResource
{
   protected String destination;
   protected PostMessage sender;
   protected SubscriptionsResource subscriptions;

   public void start() throws Exception
   {
   }

   public void stop()
   {
      subscriptions.stop();
   }

   public PostMessage getSender()
   {
      return sender;
   }

   public void setSender(PostMessage sender)
   {
      this.sender = sender;
   }

   @GET
   @Produces("application/xml")
   public Response get(@Context UriInfo uriInfo)
   {


      String msg = "<topic>"
              + "<name>" + destination + "</name>"
              + "</topic/>";
      Response.ResponseBuilder builder = Response.ok(msg);
      setSenderLink(builder, uriInfo);
      setSubscriptionsLink(builder, uriInfo);
      return builder.build();
   }

   @HEAD
   @Produces("application/xml")
   public Response head(@Context UriInfo uriInfo)
   {
      Response.ResponseBuilder builder = Response.ok();
      setSenderLink(builder, uriInfo);
      setSubscriptionsLink(builder, uriInfo);
      return builder.build();
   }

   protected void setSenderLink(Response.ResponseBuilder response, UriInfo info)
   {
      UriBuilder builder = info.getRequestUriBuilder();
      builder.path("create");
      String uri = builder.build().toString();
      LinkHeaderSupport.setLinkHeader(response, "create", "create", uri, null);
   }

   protected void setSubscriptionsLink(Response.ResponseBuilder response, UriInfo info)
   {
      UriBuilder builder = info.getRequestUriBuilder();
      builder.path("subscriptions");
      String uri = builder.build().toString();
      LinkHeaderSupport.setLinkHeader(response, "subscriptions", "subscriptions", uri, null);
   }


   public String getDestination()
   {
      return destination;
   }

   public void setDestination(String destination)
   {
      this.destination = destination;
   }

   public void setSubscriptions(SubscriptionsResource subscriptions)
   {
      this.subscriptions = subscriptions;
   }

   @Path("create")
   public Object post() throws Exception
   {
      return sender;
   }


   @Path("subscriptions")
   public SubscriptionsResource getSubscriptions()
   {
      return subscriptions;
   }
}