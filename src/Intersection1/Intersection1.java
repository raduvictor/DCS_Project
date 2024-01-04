package Intersection1;

import Components.*;
import DataObjects.DataCar;
import DataObjects.DataCarQueue;
import DataObjects.DataString;
import DataObjects.DataTransfer;
import DataOnly.Car;
import DataOnly.TransferOperation;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;
import GUIs.InputCar;

public class Intersection1 {
    public static void main(String[] args) {
        PetriNet pn = new PetriNet();
        pn.PetriNetName = "Intersection 1";

        pn.NetworkPort = 1080;

        DataCar p0 = new DataCar();
        p0.SetName("P0");
        pn.PlaceList.add(p0);

        DataCar op3 = new DataCar();
        op3.SetName("OP3");
        pn.PlaceList.add(op3);

        DataCarQueue p1 = new DataCarQueue();
        p1.Value.Size = 3;
        p1.SetName("P1");
        pn.PlaceList.add(p1);

        DataString p_tl4 = new DataString();
        p_tl4.SetName("P_TL4");
        pn.PlaceList.add(p_tl4);

        DataCar p2 = new DataCar();
        p2.SetName("P2");
        pn.PlaceList.add(p2);

        DataCarQueue p5 = new DataCarQueue();
        p5.Value.Size = 3;
        p5.SetName("P5");
        pn.PlaceList.add(p5);

        DataCarQueue p7 = new DataCarQueue();
        p7.Value.Size = 3;
        p7.SetName("P7");
        pn.PlaceList.add(p7);

        DataCar p8 = new DataCar();
        p8.SetName("P8");
        pn.PlaceList.add(p8);

        DataCar p17 = new DataCar();
        p17.SetName("P17");
        pn.PlaceList.add(p17);

        DataCarQueue p18 = new DataCarQueue();
        p18.Value.Size = 3;
        p18.SetName("P18");
        pn.PlaceList.add(p18);

        DataCar p19 = new DataCar();
        p19.SetName("P19");
        pn.PlaceList.add(p19);

        DataCar p_tl20 = new DataCar();
        p_tl20.SetName("P_TL20");
        pn.PlaceList.add(p_tl20);

        DataCar op21 = new DataCar();
        op21.SetName("OP21");
        pn.PlaceList.add(op21);

        DataCar p11 = new DataCar();
        p11.SetName("P11");
        pn.PlaceList.add(p11);

        DataCar p_tl16 = new DataCar();
        p_tl16.SetName("P_TL16");
        pn.PlaceList.add(p_tl16);

        DataCarQueue p10 = new DataCarQueue();
        p10.Value.Size = 3;
        p10.SetName("P10");
        pn.PlaceList.add(p10);

        DataCar op15 = new DataCar();
        op15.SetName("OP15");
        pn.PlaceList.add(op15);

        DataCar p9 = new DataCar();
        p9.SetName("P9");
        pn.PlaceList.add(p9);

        DataCarQueue p22 = new DataCarQueue();
        p22.Value.Size = 3;
        p22.SetName("P22");
        pn.PlaceList.add(p22);

        DataCar p23 = new DataCar();
        p23.SetName("P23");
        pn.PlaceList.add(p23);

        DataCarQueue p6 = new DataCarQueue();
        p6.Value.Size = 3;
        p6.SetName("P6");
        pn.PlaceList.add(p6);

        DataCar p30 = new DataCar();
        p30.SetName("P30");
        pn.PlaceList.add(p30);

        //TRANSITIONS______________________________________________________________________

        // T0 -------------------
        PetriTransition t0 = new PetriTransition(pn);
        t0.TransitionName = "T0";
        t0.InputPlaceName.add("P0");
        t0.InputPlaceName.add("P1");

        Condition T0Ct1 = new Condition(t0, "P0", TransitionCondition.NotNull);
        Condition T0Ct2 = new Condition(t0, "P1", TransitionCondition.CanAddCars);
        T0Ct1.SetNextCondition(LogicConnector.AND, T0Ct2);

        GuardMapping grdT0 = new GuardMapping();
        grdT0.condition = T0Ct1;
        grdT0.Activations.add(new Activation(t0, "P0", TransitionOperation.AddElement, "P1"));
        t0.GuardMappingList.add(grdT0);

        Condition T0Ct3 = new Condition(t0, "P_a1", TransitionCondition.NotNull);
        Condition T0Ct4 = new Condition(t0, "P_x1", TransitionCondition.CanNotAddCars);
        T0Ct3.SetNextCondition(LogicConnector.AND, T0Ct4);

        GuardMapping grdT0_1 = new GuardMapping();
        grdT0_1.condition = T0Ct3;
        grdT0_1.Activations.add(new Activation(t0, "full", TransitionOperation.SendOverNetwork, "OP3"));
        grdT0_1.Activations.add(new Activation(t0, "P0", TransitionOperation.Move, "P0")); //Copy -> Move
        t0.GuardMappingList.add(grdT0_1);

        t0.Delay = 0;
        pn.Transitions.add(t0);

        //T1

        PetriTransition t1 = new PetriTransition(pn);
        t1.TransitionName = "T1";
        t1.InputPlaceName.add("P1");
        t1.InputPlaceName.add("P_TL4");

        Condition T1Ct0 = new Condition(t1, "P_TL4", TransitionCondition.NotNull);
        Condition T1Ct1 = new Condition(t1, "P1", TransitionCondition.NotNull);
        T1Ct0.SetNextCondition(LogicConnector.AND, T1Ct1);

        GuardMapping grdT1 = new GuardMapping();
        grdT1.condition = T1Ct0;
        grdT1.Activations.add(new Activation(t1, "P1", TransitionOperation.AddElement, "P2"));

        pn.Transitions.add(t1);

        //T2

        PetriTransition t2 = new PetriTransition(pn);
        t2.TransitionName = "T2";
        t2.InputPlaceName.add("P2");
        t2.InputPlaceName.add("P5");

        Condition T2Ct0 = new Condition(t2, "P2", TransitionCondition.NotNull);
        Condition T2Ct1 = new Condition(t2, "P5", TransitionCondition.CanAddCars);
        T2Ct0.SetNextCondition(LogicConnector.AND, T2Ct1);

        GuardMapping grdT2 = new GuardMapping();
        grdT2.condition = T2Ct0;
        grdT2.Activations.add(new Activation(t2, "P2", TransitionOperation.AddElement, "P5"));

        pn.Transitions.add(t2);

        // T3

        PetriTransition t3 = new PetriTransition(pn);
        t3.TransitionName = "T3";
        t3.InputPlaceName.add("P7");
        t3.InputPlaceName.add("P5");

        Condition T3Ct0 = new Condition(t3, "P7", TransitionCondition.NotNull);
        Condition T3Ct1 = new Condition(t3, "P5", TransitionCondition.CanAddCars);
        T3Ct0.SetNextCondition(LogicConnector.AND, T3Ct1);

        GuardMapping grdT3 = new GuardMapping();
        grdT3.condition = T3Ct0;
        grdT3.Activations.add(new Activation(t3, "P7", TransitionOperation.PopElementWithoutTarget, "P5"));

        pn.Transitions.add(t3);

// T6

        PetriTransition t6 = new PetriTransition(pn);
        t6.TransitionName = "T6";
        t6.InputPlaceName.add("P11");
        t6.InputPlaceName.add("P5");

        Condition T6Ct0 = new Condition(t6, "P11", TransitionCondition.NotNull);
        Condition T6Ct1 = new Condition(t6, "P5", TransitionCondition.CanAddCars);
        T6Ct0.SetNextCondition(LogicConnector.AND, T6Ct1);

        GuardMapping grdT6 = new GuardMapping();
        grdT6.condition = T6Ct0;
        grdT6.Activations.add(new Activation(t6, "P11", TransitionOperation.AddElement, "P5"));

        pn.Transitions.add(t6);

// T10

        PetriTransition t10 = new PetriTransition(pn);
        t10.TransitionName = "T10";
        t10.InputPlaceName.add("P10");
        t10.InputPlaceName.add("P11");

        Condition T10Ct0 = new Condition(t10, "P10", TransitionCondition.NotNull);
        Condition T10Ct1 = new Condition(t10, "P11", TransitionCondition.CanAddCars);
        T10Ct0.SetNextCondition(LogicConnector.AND, T10Ct1);

        GuardMapping grdT10 = new GuardMapping();
        grdT10.condition = T10Ct0;
        grdT10.Activations.add(new Activation(t10, "P10", TransitionOperation.AddElement, "P11"));

        pn.Transitions.add(t10);

// T11

        PetriTransition t11 = new PetriTransition(pn);
        t11.TransitionName = "T11";
        t11.InputPlaceName.add("P22");
        t11.InputPlaceName.add("P5");

        Condition T11Ct0 = new Condition(t11, "P22", TransitionCondition.NotNull);
        Condition T11Ct1 = new Condition(t11, "P5", TransitionCondition.CanAddCars);
        T11Ct0.SetNextCondition(LogicConnector.AND, T11Ct1);

        GuardMapping grdT11 = new GuardMapping();
        grdT11.condition = T11Ct0;
        grdT11.Activations.add(new Activation(t11, "P22", TransitionOperation.PopElementWithoutTarget, "P5"));

        pn.Transitions.add(t11);

        // T5

        PetriTransition t5 = new PetriTransition(pn);
        t5.TransitionName = "T5";
        t5.InputPlaceName.add("P6");
        t5.InputPlaceName.add("P5");

        Condition T5Ct0 = new Condition(t5, "P6", TransitionCondition.NotNull);
        Condition T5Ct1 = new Condition(t5, "P5", TransitionCondition.CanAddCars);
        T5Ct0.SetNextCondition(LogicConnector.AND, T5Ct1);

        GuardMapping grdT5 = new GuardMapping();
        grdT5.condition = T5Ct0;
        grdT5.Activations.add(new Activation(t5, "P6", TransitionOperation.AddElement, "P5"));

        pn.Transitions.add(t5);

        // T7 -------------------
        PetriTransition t7 = new PetriTransition(pn);
        t7.TransitionName = "T7";
        t7.InputPlaceName.add("P11");
        t7.InputPlaceName.add("P10");
        t7.InputPlaceName.add("P_TL16");

        Condition T7Ct1 = new Condition(t7, "P10", TransitionCondition.NotNull);

        GuardMapping grdT7 = new GuardMapping();
        grdT7.condition = T7Ct1;
        grdT7.Activations.add(new Activation(t7, "P10", TransitionOperation.AddElement, "P11"));
        t7.GuardMappingList.add(grdT7);

        t7.Delay = 0;
        pn.Transitions.add(t7);

// T8 -------------------
        PetriTransition t8 = new PetriTransition(pn);
        t8.TransitionName = "T8";
        t8.InputPlaceName.add("P9");
        t8.InputPlaceName.add("P10");

        Condition T8Ct1 = new Condition(t8, "P9", TransitionCondition.NotNull);
        Condition T8Ct2 = new Condition(t8, "P10", TransitionCondition.CanAddCars);
        T8Ct1.SetNextCondition(LogicConnector.AND, T8Ct2);

        GuardMapping grdT8 = new GuardMapping();
        grdT8.condition = T8Ct1;
        grdT8.Activations.add(new Activation(t8, "P9", TransitionOperation.AddElement, "P10"));
        t8.GuardMappingList.add(grdT8);

        t8.Delay = 0;
        pn.Transitions.add(t8);

// T13 -------------------
        PetriTransition t13 = new PetriTransition(pn);
        t13.TransitionName = "T13";
        t13.InputPlaceName.add("P17");
        t13.InputPlaceName.add("P18");

        Condition T13Ct1 = new Condition(t13, "P17", TransitionCondition.NotNull);
        Condition T13Ct2 = new Condition(t13, "P18", TransitionCondition.CanAddCars);
        T13Ct1.SetNextCondition(LogicConnector.AND, T13Ct2);

        GuardMapping grdT13 = new GuardMapping();
        grdT13.condition = T13Ct1;
        grdT13.Activations.add(new Activation(t13, "P17", TransitionOperation.AddElement, "P_TL20"));
        t13.GuardMappingList.add(grdT13);

        t13.Delay = 0;
        pn.Transitions.add(t13);

// T14 -------------------
        PetriTransition t14 = new PetriTransition(pn);
        t14.TransitionName = "T14";
        t14.InputPlaceName.add("P18");

        Condition T14Ct1 = new Condition(t14, "P18", TransitionCondition.NotNull);

        GuardMapping grdT14 = new GuardMapping();
        grdT14.condition = T14Ct1;
        grdT14.Activations.add(new Activation(t14, "P18", TransitionOperation.AddElement, "P19"));
        grdT14.Activations.add(new Activation(t14, "P18", TransitionOperation.AddElement, "OP21"));
        t14.GuardMappingList.add(grdT14);

        t14.Delay = 0;
        pn.Transitions.add(t14);

        // T4
        PetriTransition t4 = new PetriTransition(pn);
        t4.TransitionName = "T4";
        t4.InputPlaceName.add("P7");

        Condition T4Ct0 = new Condition(t4, "P7", TransitionCondition.NotNull);

        GuardMapping grdT4 = new GuardMapping();
        grdT4.condition = T4Ct0;
        grdT4.Activations.add(new Activation(t4, "P7", TransitionOperation.AddElement, "P8"));

        pn.Transitions.add(t4);

// T12
        PetriTransition t12 = new PetriTransition(pn);
        t12.TransitionName = "T12";
        t12.InputPlaceName.add("P22");

        Condition T12Ct0 = new Condition(t12, "P22", TransitionCondition.NotNull);

        GuardMapping grdT12 = new GuardMapping();
        grdT12.condition = T12Ct0;
        grdT12.Activations.add(new Activation(t12, "P22", TransitionOperation.AddElement, "P23"));

        pn.Transitions.add(t12);

// T9
        PetriTransition t9 = new PetriTransition(pn);
        t9.TransitionName = "T9";
        t9.InputPlaceName.add("P6");

        Condition T9Ct0 = new Condition(t9, "P6", TransitionCondition.NotNull);

        GuardMapping grdT9 = new GuardMapping();
        grdT9.condition = T9Ct0;
        grdT9.Activations.add(new Activation(t9, "P6", TransitionOperation.AddElement, "P30"));

        pn.Transitions.add(t9);

        System.out.println("Exp1 started \n ------------------------------");
        pn.Delay = 2000;
        pn.Start();

        PetriNetWindow frame = new PetriNetWindow(false);
        frame.petriNet = pn;
        frame.setVisible(true);

        // Intersection2.Intersection2.main(args); // Start the second controller
        InputCar.main(args); // Start the input car GUI
    }
}