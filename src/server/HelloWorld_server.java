package server;

import sample.*;

import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.CORBA.portable.*;
import org.omg.PortableServer.*;
 
 
class HelloWorldServant extends HelloWorldPOA {
    public String sayHello() {
        return "HelloWorld\n";
    }
}

public class HelloWorld_server {

	public static void main(String args[]) {
        try {
            //Initialize CORBA
            ORB orb = ORB.init(args, null);
 
            //接下来要做的事转码处理
            java.util.Properties p = new java.util.Properties();
            p.setProperty("com.sun.CORBA.codeset.charsets", "0x05010001, 0x00010109"); // UTF-8, UTF-16
            p.setProperty("com.sun.CORBA.codeset.wcharsets", "0x00010109, 0x05010001"); // UTF-16, UTF-8
            orb = org.omg.CORBA_2_3.ORB.init(args, p);
 
            org.omg.CORBA.Object poaobj = orb.resolve_initial_references(
                    "RootPOA");
            org.omg.PortableServer.POA rootPOA = org.omg.PortableServer.
                                                 POAHelper.narrow(poaobj);
            org.omg.PortableServer.POAManager manager = rootPOA.the_POAManager();
 
            //New Servant
            HelloWorldServant objRef = new HelloWorldServant();
            HelloWorld obj = objRef._this(orb);
 
            //Binding
            NamingContext ncRef = NamingContextHelper.narrow(
                    orb.resolve_initial_references("NameService"));
            NameComponent nc = new NameComponent("HelloWorld", "");
            NameComponent path[] = {nc};
            ncRef.rebind(path, obj);
 
            //Run
            manager.activate();
            System.out.println("HelloWorld is running!");
            orb.run();
        } catch (Exception e) {
            System.err.println("ERROR: " + e);
            e.printStackTrace(System.out);
        }
    }
	
}
