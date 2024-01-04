package Intersection2;

import Components.PetriNet;
import DataObjects.DataInteger;
import DataObjects.DataString;
import DataObjects.DataTransfer;
import DataOnly.TransferOperation;

public class Controller {
    public static void main (String []args) {
        PetriNet pn = new PetriNet();
        pn.PetriNetName = "Controller";
        pn.SetName("Controller");
        pn.NetworkPort = 1081;

        // Input locations
        DataString ini = new DataString();
        ini.SetName("ini");
        ini.SetValue("R");
        pn.PlaceList.add(ini);

        DataString in4 = new DataString();
        in4.SetName("in4");
        pn.PlaceList.add(in4);

        DataString in5 = new DataString();
        in5.SetName("in5");
        pn.PlaceList.add(in5);

        DataString in6 = new DataString();
        in6.SetName("in6");
        pn.PlaceList.add(in6);

        // P locations
        DataString p1 = new DataString();
        p1.SetName("R1 R2 R3");
        // p1.SetValue("signal");
        pn.PlaceList.add(p1);

        DataString p2 = new DataString();
        p2.SetName("G1 R2 R3");
        pn.PlaceList.add(p2);

        DataString p3 = new DataString();
        p3.SetName("Y1 R2 R3");
        pn.PlaceList.add(p3);

        DataString p4 = new DataString();
        p4.SetName("R1 G2 R3");
        pn.PlaceList.add(p4);

        DataString p5 = new DataString();
        p5.SetName("R1 Y2 R3");
        pn.PlaceList.add(p5);

        DataString p6 = new DataString();
        p6.SetName("R1 R2 G3");
        pn.PlaceList.add(p6);

        DataString p7 = new DataString();
        p7.SetName("R1 R2 Y3");
        pn.PlaceList.add(p7);

        // Control locations
        DataTransfer p8 = new DataTransfer();
        p8.SetName("OP4");
        p8.Value = new TransferOperation("localhost", "1080" , "P_TL4");
        pn.PlaceList.add(p8);

        DataTransfer p9 = new DataTransfer();
        p9.SetName("OP5");
        p9.Value = new TransferOperation("localhost", "1080" , "P_TL5");
        pn.PlaceList.add(p9);

        DataTransfer p10 = new DataTransfer();
        p10.SetName("OP6");
        p10.Value = new TransferOperation("localhost", "1080" , "P_TL6");
        pn.PlaceList.add(p10);
    }
}
