package Intersection2;

import Components.*;
import DataObjects.DataInteger;
import DataObjects.DataString;
import DataObjects.DataTransfer;
import DataOnly.TransferOperation;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

public class Controller {
    public static void main (String []args) {
        PetriNet petriNet = new PetriNet();
        petriNet.PetriNetName = "Controller";
        petriNet.SetName("Controller 2");
        petriNet.NetworkPort = 1083;

        // Input locations
        DataString ini = new DataString();
        ini.SetName("ini");
        ini.SetValue("R");
        petriNet.PlaceList.add(ini);

        DataString in4 = new DataString();
        in4.SetName("in4");
        petriNet.PlaceList.add(in4);

        DataString in5 = new DataString();
        in5.SetName("in5");
        petriNet.PlaceList.add(in5);

        DataString in6 = new DataString();
        in6.SetName("in6");
        petriNet.PlaceList.add(in6);

        // P locations
        DataString p1 = new DataString();
        p1.SetName("R1 R2 R3");
        // p1.SetValue("signal");
        petriNet.PlaceList.add(p1);

        DataString p2 = new DataString();
        p2.SetName("G1 R2 R3");
        petriNet.PlaceList.add(p2);

        DataString p3 = new DataString();
        p3.SetName("Y1 R2 R3");
        petriNet.PlaceList.add(p3);

        DataString p4 = new DataString();
        p4.SetName("R1 G2 R3");
        petriNet.PlaceList.add(p4);

        DataString p5 = new DataString();
        p5.SetName("R1 Y2 R3");
        petriNet.PlaceList.add(p5);

        DataString p6 = new DataString();
        p6.SetName("R1 R2 G3");
        petriNet.PlaceList.add(p6);

        DataString p7 = new DataString();
        p7.SetName("R1 R2 Y3");
        petriNet.PlaceList.add(p7);

        // Control locations
        DataTransfer p8 = new DataTransfer();
        p8.SetName("OP4");
        p8.Value = new TransferOperation("localhost", "1081" , "P_TL4");
        petriNet.PlaceList.add(p8);

        DataTransfer p9 = new DataTransfer();
        p9.SetName("OP5");
        p9.Value = new TransferOperation("localhost", "1081" , "P_TL5");
        petriNet.PlaceList.add(p9);

        DataTransfer p10 = new DataTransfer();
        p10.SetName("OP6");
        p10.Value = new TransferOperation("localhost", "1081" , "P_TL6");
        petriNet.PlaceList.add(p10);

        // Secondary locations (middleware like, helper locations)
        DataString R = new DataString();
        R.SetName("R");
        R.SetValue("R");
        petriNet.ConstantPlaceList.add(R);

        DataString Y = new DataString();
        Y.SetName("Y");
        Y.SetValue("Y");
        petriNet.ConstantPlaceList.add(Y);

        DataString G = new DataString();
        G.SetName("G");
        G.SetValue("G");
        petriNet.ConstantPlaceList.add(G);

        DataInteger Five = new DataInteger();
        Five.SetName("Delay_Five");
        Five.SetValue(500);
        petriNet.ConstantPlaceList.add(Five);

        DataInteger Ten = new DataInteger();
        Ten.SetName("Delay_Ten");
        Ten.SetValue(1000);
        petriNet.ConstantPlaceList.add(Ten);

        // Transitions
        // T init -> T7
        PetriTransition t7 = new PetriTransition(petriNet);
        t7.TransitionName = "T7";

        Condition T7Ct0 = new Condition(t7, "ini", TransitionCondition.NotNull);

        GuardMapping grdT7 = new GuardMapping();
        grdT7.condition = T7Ct0;

        grdT7.Activations.add(new Activation(t7, "ini", TransitionOperation.SendOverNetwork, "OP1"));
        grdT7.Activations.add(new Activation(t7, "ini", TransitionOperation.SendOverNetwork, "OP2"));
        grdT7.Activations.add(new Activation(t7, "ini", TransitionOperation.SendOverNetwork, "OP3"));
        grdT7.Activations.add(new Activation(t7, "", TransitionOperation.MakeNull, "ini"));

        t7.GuardMappingList.add(grdT7);
        t7.Delay = 0;
        petriNet.Transitions.add(t7);

        // T0
        PetriTransition t0 = new PetriTransition(petriNet);
        t0.TransitionName = "T0";
        t0.InputPlaceName.add("R1 R2 R3");

        Condition T0Ct0 = new Condition(t0, "R1 R2 R3", TransitionCondition.NotNull);

        GuardMapping grdT0 = new GuardMapping();
        grdT0.condition= T0Ct0;
        grdT0.Activations.add(new Activation(t0, "R1 R2 R3", TransitionOperation.Move, "G1 R2 R3"));
        grdT0.Activations.add(new Activation(t0, "G", TransitionOperation.SendOverNetwork, "OP4"));
        grdT0.Activations.add(new Activation(t0, "Delay_Ten", TransitionOperation.DynamicDelay,""));
        t0.GuardMappingList.add(grdT0);

        petriNet.Transitions.add(t0);

        // T1
        PetriTransition t1 = new PetriTransition(petriNet);
        t1.TransitionName = "T1";
        t1.InputPlaceName.add("G1 R2 R3");

        Condition T1Ct0 = new Condition(t1, "G1 R2 R3", TransitionCondition.NotNull);
        Condition T1Ct1 = new Condition(t1, "in4", TransitionCondition.NotNull);
        T1Ct0.SetNextCondition(LogicConnector.AND, T1Ct1);

        GuardMapping grdT1 = new GuardMapping();
        grdT1.condition = T1Ct0;
        grdT1.Activations.add(new Activation(t1, "G1 R2 R3", TransitionOperation.Move, "Y1 R2 R3"));
        grdT1.Activations.add(new Activation(t1, "Y", TransitionOperation.SendOverNetwork, "OP4"));
        grdT1.Activations.add(new Activation(t1, "Delay_Ten", TransitionOperation.DynamicDelay, ""));

        Condition T1Ct2 = new Condition(t1, "G1 R2 R3", TransitionCondition.NotNull);
        Condition T1Ct3 = new Condition(t1, "in4", TransitionCondition.IsNull);
        T1Ct2.SetNextCondition(LogicConnector.AND, T1Ct3);

        GuardMapping grdT1_1 = new GuardMapping();
        grdT1_1.condition = T1Ct2;

        grdT1_1.Activations.add(new Activation(t1, "G1 R2 R3", TransitionOperation.Move, "Y1 R2 R3"));
        grdT1_1.Activations.add(new Activation(t1, "Y", TransitionOperation.SendOverNetwork, "OP4"));
        grdT1_1.Activations.add(new Activation(t1, "Delay_Five", TransitionOperation.DynamicDelay, ""));

        t1.GuardMappingList.add(grdT1);
        t1.GuardMappingList.add(grdT1_1);

        petriNet.Transitions.add(t1);

        // T2
    }
}
