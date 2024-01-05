package Intersection1;

import Components.*;
import DataObjects.DataInteger;
import DataObjects.DataString;
import DataObjects.DataTransfer;
import DataOnly.TransferOperation;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

public class Controller {
    public static void main(String[] args) {
        PetriNet petriNet = new PetriNet();
        petriNet.PetriNetName = "Controller";
        petriNet.SetName("Controller 1");
        petriNet.NetworkPort = 1082;

        // input locations

        DataString ini = new DataString();
        ini.SetName("ini");
        ini.SetValue("R");
        petriNet.ConstantPlaceList.add(ini);

        DataString in1 = new DataString();
        in1.SetName("in1");
        petriNet.PlaceList.add(in1);

        DataString in2 = new DataString();
        in2.SetName("in2");
        petriNet.PlaceList.add(in2);

        DataString in3 = new DataString();
        in2.SetName("in3");
        petriNet.PlaceList.add(in3);

        // P locations

        DataString p1 = new DataString();
        p1.SetName("R1 R2 R3");
//        p1.SetValue("signal");
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

        DataTransfer p8 = new DataTransfer();
        p8.SetName("OP1");
        p8.Value = new TransferOperation("localhost", "1080", "P_TL1");
        petriNet.PlaceList.add(p8);

        DataTransfer p9 = new DataTransfer();
        p9.SetName("OP2");
        p9.Value = new TransferOperation("localhost", "1080", "P_TL2");
        petriNet.PlaceList.add(p9);

        DataTransfer p10 = new DataTransfer();
        p10.SetName("OP3");
        p10.Value = new TransferOperation("localhost", "1080", "P_TL3");
        petriNet.PlaceList.add(p10);

        // Secondary locations ( middleware like, helper locations )
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

        DataInteger Delay_Five = new DataInteger();
        Delay_Five.SetName("Delay_Five");
        Delay_Five.SetValue(500);
        petriNet.ConstantPlaceList.add(Delay_Five);

        DataInteger Delay_Ten = new DataInteger();
        Delay_Ten.SetName("Delay_Ten");
        Delay_Ten.SetValue(1000);
        petriNet.ConstantPlaceList.add(Delay_Ten);

        // T init -> T7 -----------------------------------------------------------------------------------

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

        // T0 ---------------------------------------------------------------------------------------------

        PetriTransition t0 = new PetriTransition(petriNet);
        t0.TransitionName = "T0";
        t0.InputPlaceName.add("R1 R2 R3");

        Condition T0Ct0 = new Condition(t0, "R1 R2 R3", TransitionCondition.NotNull);

        GuardMapping grdT0 = new GuardMapping();
        grdT0.condition = T0Ct0;

        grdT0.Activations.add(new Activation(t0, "R1 R2 R3", TransitionOperation.Move, "G1 R2 R3"));
        grdT0.Activations.add(new Activation(t0, "G", TransitionOperation.SendOverNetwork, "OP1"));
        grdT0.Activations.add(new Activation(t0, "Delay_Ten", TransitionOperation.DynamicDelay, ""));
        t0.GuardMappingList.add(grdT0);

        petriNet.Transitions.add(t0);

        // T1 ---------------------------------------------------------------------------------------------

        PetriTransition t1 = new PetriTransition(petriNet);
        t1.TransitionName = "T1";
        t1.InputPlaceName.add("G1 R2 R3");

        Condition T1Ct0 = new Condition(t1, "G1 R2 R3", TransitionCondition.NotNull);
        Condition T1Ct1 = new Condition(t1, "in1", TransitionCondition.NotNull);
        T1Ct0.SetNextCondition(LogicConnector.AND, T1Ct1);

        GuardMapping grdT1 = new GuardMapping();
        grdT1.condition = T1Ct0;

        grdT1.Activations.add(new Activation(t1, "G1 R2 R3", TransitionOperation.Move, "Y1 R2 R3"));
        grdT1.Activations.add(new Activation(t1, "Y", TransitionOperation.SendOverNetwork, "OP1"));
        grdT1.Activations.add(new Activation(t1, "Delay_Ten", TransitionOperation.DynamicDelay, ""));


        Condition T1Ct2 = new Condition(t1, "G1 R2 R3", TransitionCondition.NotNull);
        Condition T1Ct3 = new Condition(t1, "G1 R2 R3", TransitionCondition.IsNull);
        T1Ct2.SetNextCondition(LogicConnector.AND, T1Ct3);

        GuardMapping grdT1_1 = new GuardMapping();
        grdT1_1.condition = T1Ct2;

        grdT1_1.Activations.add(new Activation(t1, "G1 R2 R3", TransitionOperation.Move, "Y1 R2 R3"));
        grdT1_1.Activations.add(new Activation(t1, "Y", TransitionOperation.SendOverNetwork, "OP1"));
        grdT1_1.Activations.add(new Activation(t1, "Delay_Five", TransitionOperation.DynamicDelay, ""));

        t1.GuardMappingList.add(grdT1);
        t1.GuardMappingList.add(grdT1_1);

        petriNet.Transitions.add(t1);

        // T2 ---------------------------------------------------------------------------------------------

        PetriTransition t2 = new PetriTransition(petriNet);
        t2.TransitionName = "T2";
        t2.InputPlaceName.add("Y1 R2 R3");

        Condition T2Ct0 = new Condition(t2, "Y1 R2 R3", TransitionCondition.NotNull);

        GuardMapping grdT2 = new GuardMapping();
        grdT2.condition = T2Ct0;
        grdT2.Activations.add(new Activation(t2, "Y1 R2 R3", TransitionOperation.Move, "R1 G2 R3"));
        grdT2.Activations.add(new Activation(t2, "R", TransitionOperation.SendOverNetwork, "OP1"));
        grdT2.Activations.add(new Activation(t2, "G", TransitionOperation.SendOverNetwork, "OP2"));
        grdT2.Activations.add(new Activation(t2, "Delay_Ten", TransitionOperation.DynamicDelay, ""));
        t2.GuardMappingList.add(grdT2);

        petriNet.Transitions.add(t2);

        // T3 ---------------------------------------------------------------------------------------------

        PetriTransition t3 = new PetriTransition(petriNet);
        t3.TransitionName = "T3";
        t3.InputPlaceName.add("R1 G2 R3");

        Condition T3Ct0 = new Condition(t3, "R1 G2 R3", TransitionCondition.NotNull);
        Condition T3Ct1 = new Condition(t3, "in2", TransitionCondition.NotNull);
        T3Ct0.SetNextCondition(LogicConnector.AND, T3Ct1);

        GuardMapping grdT3 = new GuardMapping();
        grdT3.condition = T3Ct0;
        grdT3.Activations.add(new Activation(t3, "R1 G2 R3", TransitionOperation.Move, "R1 Y2 R3"));
        grdT3.Activations.add(new Activation(t3, "Y", TransitionOperation.SendOverNetwork, "OP2"));
        grdT3.Activations.add(new Activation(t3, "Delay_Ten", TransitionOperation.DynamicDelay, ""));

        Condition T3Ct2 = new Condition(t3, "R1 G2 R3", TransitionCondition.NotNull);
        Condition T3Ct3 = new Condition(t3, "R1 G2 R3", TransitionCondition.IsNull);
        T3Ct2.SetNextCondition(LogicConnector.AND, T3Ct3);

        GuardMapping grdT3_1 = new GuardMapping();
        grdT3_1.condition = T3Ct2;
        grdT3_1.Activations.add(new Activation(t3, "R1 G2 R3", TransitionOperation.Move, "R1 Y2 R3"));
        grdT3_1.Activations.add(new Activation(t3, "Y", TransitionOperation.SendOverNetwork, "OP2"));
        grdT3_1.Activations.add(new Activation(t3, "Delay_Five", TransitionOperation.DynamicDelay, ""));

        t3.GuardMappingList.add(grdT3);
        t3.GuardMappingList.add(grdT3_1);

        petriNet.Transitions.add(t3);

        // T4 ---------------------------------------------------------------------------------------------

        PetriTransition t4 = new PetriTransition(petriNet);
        t4.TransitionName = "T4";
        t4.InputPlaceName.add("R2 Y2 R3");

        Condition T4Ct0 = new Condition(t4, "R2 Y2 R3", TransitionCondition.NotNull);

        GuardMapping grdT4 = new GuardMapping();
        grdT4.condition = T4Ct0;
        grdT4.Activations.add(new Activation(t4, "R2 Y2 R3", TransitionOperation.Move, "R1 R2 G3"));
        grdT4.Activations.add(new Activation(t4, "R", TransitionOperation.SendOverNetwork, "OP2"));
        grdT4.Activations.add(new Activation(t4, "G", TransitionOperation.SendOverNetwork, "OP3"));
        grdT4.Activations.add(new Activation(t4, "Delay_Ten", TransitionOperation.DynamicDelay, ""));

        t4.GuardMappingList.add(grdT4);

        petriNet.Transitions.add(t4);

        // T5 ---------------------------------------------------------------------------------------------

        PetriTransition t5 = new PetriTransition(petriNet);
        t5.TransitionName = "T5";
        t5.InputPlaceName.add("R1 R2 G3");

        Condition T5Ct0 = new Condition(t5, "R1 R2 G3", TransitionCondition.NotNull);
        Condition T5Ct1 = new Condition(t5, "in3", TransitionCondition.NotNull);
        T5Ct0.SetNextCondition(LogicConnector.AND, T5Ct1);

        GuardMapping grdT5 = new GuardMapping();
        grdT5.condition = T5Ct0;
        grdT5.Activations.add(new Activation(t5, "R1 R2 G3", TransitionOperation.Move, "R1 R2 Y3"));
        grdT5.Activations.add(new Activation(t5, "Y", TransitionOperation.SendOverNetwork, "OP3"));
        grdT5.Activations.add(new Activation(t5, "Delay_Ten", TransitionOperation.DynamicDelay, ""));
        
        Condition T5Ct2 = new Condition(t5, "R1 R2 G3", TransitionCondition.NotNull);
        Condition T5Ct3 = new Condition(t5, "in3", TransitionCondition.IsNull);
        T5Ct2.SetNextCondition(LogicConnector.AND, T5Ct3);

        GuardMapping grdT5_1 = new GuardMapping();
        grdT5_1.condition = T5Ct2;
        grdT5_1.Activations.add(new Activation(t5, "R1 R2 G3", TransitionOperation.Move, "R1 R2 Y3"));
        grdT5_1.Activations.add(new Activation(t5, "Y", TransitionOperation.SendOverNetwork, "OP3"));
        grdT5_1.Activations.add(new Activation(t5, "Delay_Five", TransitionOperation.DynamicDelay, ""));

        t5.GuardMappingList.add(grdT5);
        t5.GuardMappingList.add(grdT5_1);

        petriNet.Transitions.add(t5);

        // T6 ---------------------------------------------------------------------------------------------

        PetriTransition t6 = new PetriTransition(petriNet);
        t6.TransitionName = "T6";
        t6.InputPlaceName.add("R1 R2 Y3");

        Condition T6Ct0 = new Condition(t6, "R1 R2 Y3", TransitionCondition.NotNull);

        GuardMapping grdT6 = new GuardMapping();
        grdT6.condition = T6Ct0;
        grdT6.Activations.add(new Activation(t6, "R1 R2 Y3", TransitionOperation.Move, "R1 R2 R3"));
        grdT6.Activations.add(new Activation(t6, "R", TransitionOperation.SendOverNetwork, "OP3"));
        grdT6.Activations.add(new Activation(t6, "Delay_Ten", TransitionOperation.DynamicDelay, ""));

        t6.GuardMappingList.add(grdT6);

        petriNet.Transitions.add(t6);

        // PetriNet Start -------------------------------------------------------------------------------

        System.out.println("Exp1 started \n ------------------------------");
        petriNet.Delay = 2000;
        // pn.Start();

        PetriNetWindow frame = new PetriNetWindow(false);
        frame.petriNet = petriNet;
        frame.setVisible(true);
    }
}