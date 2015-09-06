package controllers;

import Actors.BasicLifecycleLoggingActor;
import Actors.StudentActor;
import Actors.StudentDelayedActor;
import Actors.TeacherActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import play.mvc.Controller;
import play.mvc.Result;

import static Protocols.StudentProtocol.InitSignal;

public class Application extends Controller {

    public Result index() throws InterruptedException {

        // Initialize the ActorSystem
        final ActorSystem actorSystem = ActorSystem.create("UniversityMessageSystem");

        // construct the Teacher Actor Ref
        final ActorRef teacherActorRef = actorSystem.actorOf(Props.create(TeacherActor.class), "teacherActorRef");

        // construct the Student Actor - pass the teacher actorref as a constructor parameter to StudentActor
        final ActorRef studentActorRef = actorSystem.actorOf(Props.create(StudentActor.class, teacherActorRef), "studentActorRef");

        // construct the Delayed Student Actor - pass the teacher actorref as a constructor parameter to StudentActor
        final ActorRef studentDelayedActorRef = actorSystem.actorOf(Props.create(StudentDelayedActor.class, teacherActorRef), "studentDelayedActorRef");

        // construct the Logging Actor
        final ActorRef lifeCycleActor = actorSystem.actorOf(Props.create(BasicLifecycleLoggingActor.class),
                "lifeCycleActor");

        InitSignal initSignal = new InitSignal();

        studentActorRef.tell(initSignal, studentActorRef);
        studentDelayedActorRef.tell(initSignal, studentActorRef);

        lifeCycleActor.tell("hello", studentActorRef);
        lifeCycleActor.tell("stop", studentActorRef);

        //Let's wait for a couple of seconds before we shut down the system
        Thread.sleep(2000);

        //Shut down the ActorSystem.
        actorSystem.shutdown();

        return ok();
    }

}
