package Intersection1;

import Components.*;
import DataObjects.DataCar;
import DataObjects.DataCarQueue;
import DataObjects.DataString;
import DataObjects.DataTransfer;
import DataOnly.TransferOperation;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

public class Intersection1 {
    public static void main(String[] args) {
        PetriNet petriNet = new PetriNet();
        petriNet.PetriNetName = "Intersection1";

        petriNet.NetworkPort = 1080;

        DataString G = new DataString();
        G.Printable = false;
        G.SetName("G");
        G.SetValue("G");
        petriNet.ConstantPlaceList.add(G);

        DataString full = new DataString();
        full.SetName("full");
        full.SetValue("full");
        petriNet.ConstantPlaceList.add(full);

        // the center of the intersection
        DataCarQueue p5 = new DataCarQueue();
        p5.Value.Size = 3;
        p5.SetName("P_5");
        petriNet.PlaceList.add(p5);


        // --------------------------------------------------------------------------------------
        // ---------------------------- Upper part of the intersection --------------------------
        // --------------------------------------------------------------------------------------
        DataCar P0 = new DataCar();
        P0.SetName("P_0");
        petriNet.PlaceList.add(P0);

        DataTransfer OP3 = new DataTransfer();
        OP3.SetName("OP_3");
        petriNet.PlaceList.add(OP3);
        OP3.Value = new TransferOperation("localhost", "1082", "in");

        DataCarQueue P1 = new DataCarQueue();
        P1.Value.Size = 3;
        P1.SetName("P_1");
        petriNet.PlaceList.add(P1);

        DataString P_TL4 = new DataString();
        P_TL4.SetName("P_TL4");
//        P_TL4.SetValue("red");
        petriNet.PlaceList.add(P_TL4);

        DataCar P2 = new DataCar();
        P2.SetName("P_2");
        petriNet.PlaceList.add(P2);


        // --------------------------------------------------------------------------------------
        // ---------------------------- Left part of the intersection --------------------------
        // --------------------------------------------------------------------------------------

        // DOWN

        DataCar P11 = new DataCar();
        P11.SetName("P_11");
        petriNet.PlaceList.add(P11);

        DataString P_TL16 = new DataString();
        P_TL16.SetName("P_TL16");
//        P_TL16.SetValue("red");
        petriNet.PlaceList.add(P_TL16);

        DataCarQueue P10 = new DataCarQueue();
        P10.Value.Size = 3;
        P10.SetName("P_10");
        petriNet.PlaceList.add(P10);

        DataTransfer OP15 = new DataTransfer();
        OP15.SetName("OP_15");
        petriNet.PlaceList.add(OP15);
        OP15.Value = new TransferOperation("localhost", "1082", "in");

        DataCar P9 = new DataCar();
        P9.SetName("P_9");
        petriNet.PlaceList.add(P9);

        // UP

        DataCarQueue P7 = new DataCarQueue();
        P7.Value.Size = 3;
        P7.SetName("P_7");
        petriNet.PlaceList.add(P7);

        DataCar P8 = new DataCar();
        P8.SetName("P_8");
        petriNet.PlaceList.add(P8);

        // --------------------------------------------------------------------------------------
        // ---------------------------- Right part of the intersection --------------------------
        // --------------------------------------------------------------------------------------

        // UP
        DataCar P17 = new DataCar();
        P17.SetName("P_17");
        petriNet.PlaceList.add(P17);

        DataString P_TL20 = new DataString();
        P_TL20.SetName("P_TL20");
//        P_TL20.SetValue("red");
        petriNet.PlaceList.add(P_TL20);

        DataCarQueue P18 = new DataCarQueue();
        P18.Value.Size = 3;
        P18.SetName("P_18");
        petriNet.PlaceList.add(P18);

        DataTransfer OP21 = new DataTransfer();
        OP21.SetName("OP_21");
        petriNet.PlaceList.add(OP21);
        OP21.Value = new TransferOperation("localhost", "1082", "in");

        // DOWN
        DataCarQueue P22 = new DataCarQueue();
        P22.Value.Size = 3;
        P22.SetName("P_22");
        petriNet.PlaceList.add(P22);

        DataCar P23 = new DataCar();
        P23.SetName("P_23");
        petriNet.PlaceList.add(P23);

        // --------------------------------------------------------------------------------------
        // ---------------------------- Lower part of the intersection --------------------------
        // --------------------------------------------------------------------------------------

        DataCarQueue P6 = new DataCarQueue();
        P6.Value.Size = 3;
        P6.SetName("P_6");
        petriNet.PlaceList.add(P6);

        DataCar P30 = new DataCar();
        P30.SetName("P_30");
        petriNet.PlaceList.add(P30);

        DataTransfer P_street1 = new DataTransfer();
        P_street1.SetName("P_street1");
        P_street1.SetValue(new TransferOperation("localhost", "1081", "P_street2"));
        petriNet.PlaceList.add(P_street1);

        // ---------------------------------------------------------------------------------------
        // ---------------------------- Transitions ----------------------------------------------
        // ---------------------------------------------------------------------------------------

        // Upper entrance -------------------------------------------------------------------------
        // T0 -----------------------------------------------------------------------------------

        PetriTransition T0 = new PetriTransition(petriNet);
        T0.TransitionName = "T0";

        T0.InputPlaceName.add("P_0");
        T0.InputPlaceName.add("P_1");

        Condition T0Ct1 = new Condition(T0, "P_0", TransitionCondition.NotNull);
        Condition T0Ct2 = new Condition(T0, "P_1", TransitionCondition.CanAddCars);
        T0Ct1.SetNextCondition(LogicConnector.AND, T0Ct2);

        GuardMapping grdT0 = new GuardMapping();
        grdT0.condition = T0Ct1;
        grdT0.Activations.add(new Activation(T0, "P_0", TransitionOperation.AddElement, "P_1"));
        T0.GuardMappingList.add(grdT0);

        Condition T0Ct3 = new Condition(T0, "P_0", TransitionCondition.NotNull);
        Condition T0Ct4 = new Condition(T0, "P_1", TransitionCondition.CanNotAddCars);
        T0Ct3.SetNextCondition(LogicConnector.AND, T0Ct4);

        GuardMapping grdT0_2 = new GuardMapping();
        grdT0_2.condition = T0Ct3;
        grdT0_2.Activations.add(new Activation(T0, "full", TransitionOperation.SendOverNetwork, "OP_3"));
        grdT0_2.Activations.add(new Activation(T0, "P_0", TransitionOperation.Move, "P_0"));
        T0.GuardMappingList.add(grdT0_2);

        T0.Delay = 0;
        petriNet.Transitions.add(T0);

        // T1 -----------------------------------------------------------------------------------

        PetriTransition T1 = new PetriTransition(petriNet);
        T1.TransitionName = "T1";

        T1.InputPlaceName.add("P_1");
        T1.InputPlaceName.add("P_TL4");

        Condition T1Ct1 = new Condition(T1, "P_TL4", TransitionCondition.Equal, "G");
        Condition T1Ct2 = new Condition(T1, "P_1", TransitionCondition.HaveCar);
        T1Ct1.SetNextCondition(LogicConnector.AND, T1Ct2);

        GuardMapping grdT1 = new GuardMapping();
        grdT1.condition = T1Ct1;
        grdT1.Activations.add(new Activation(T1, "P_1", TransitionOperation.PopElementWithoutTarget, "P_2"));
        grdT1.Activations.add(new Activation(T1, "P_TL4", TransitionOperation.Move, "P_TL4"));

        T1.GuardMappingList.add(grdT1);

        petriNet.Transitions.add(T1);

        // T2 -----------------------------------------------------------------------------------

        PetriTransition T2 = new PetriTransition(petriNet);
        T2.TransitionName = "T2";
        T2.InputPlaceName.add("P_2");
        T2.InputPlaceName.add("P_5");

        Condition T2Ct1 = new Condition(T2, "P_2", TransitionCondition.NotNull);
        Condition T2Ct2 = new Condition(T2, "P_5", TransitionCondition.CanAddCars);
        T2Ct1.SetNextCondition(LogicConnector.AND, T2Ct2);

        GuardMapping grdT2 = new GuardMapping();
        grdT2.condition = T2Ct1;
        grdT2.Activations.add(new Activation(T2, "P_2", TransitionOperation.AddElement, "P_5"));
        T2.GuardMappingList.add(grdT2);

        T2.Delay = 0;
        petriNet.Transitions.add(T2);

        // Left entrance -------------------------------------------------------------------------
        // T8 -----------------------------------------------------------------------------------

        PetriTransition T8 = new PetriTransition(petriNet);
        T8.TransitionName = "T8";

        T8.InputPlaceName.add("P_9");
        T8.InputPlaceName.add("P_10");

        Condition T8Ct1 = new Condition(T8, "P_9", TransitionCondition.NotNull);
        Condition T8Ct2 = new Condition(T8, "P_10", TransitionCondition.CanAddCars);
        T8Ct1.SetNextCondition(LogicConnector.AND, T8Ct2);

        GuardMapping grdT8 = new GuardMapping();
        grdT8.condition = T8Ct1;
        grdT8.Activations.add(new Activation(T8, "P_9", TransitionOperation.AddElement, "P_10"));
        T8.GuardMappingList.add(grdT8);

        Condition T8Ct3 = new Condition(T8, "P_9", TransitionCondition.NotNull);
        Condition T8Ct4 = new Condition(T8, "P_10", TransitionCondition.CanNotAddCars);
        T8Ct3.SetNextCondition(LogicConnector.AND, T8Ct4);

        GuardMapping grdT8_2 = new GuardMapping();
        grdT8_2.condition = T8Ct3;
        grdT8_2.Activations.add(new Activation(T8, "full", TransitionOperation.SendOverNetwork, "OP_15"));
        grdT8_2.Activations.add(new Activation(T8, "P_9", TransitionOperation.Move, "P_9"));
        T8.GuardMappingList.add(grdT8_2);

        T8.Delay = 0;
        petriNet.Transitions.add(T8);

        // T7 -----------------------------------------------------------------------------------

        PetriTransition T7 = new PetriTransition(petriNet);
        T7.TransitionName = "T7";

        T7.InputPlaceName.add("P_10");
        T7.InputPlaceName.add("P_TL16");

        Condition T7Ct1 = new Condition(T7, "P_TL16", TransitionCondition.Equal, "G");
        Condition T7Ct2 = new Condition(T7, "P_10", TransitionCondition.HaveCar);
        T7Ct1.SetNextCondition(LogicConnector.AND, T7Ct2);

        GuardMapping grdT7 = new GuardMapping();
        grdT7.condition = T7Ct1;
        grdT7.Activations.add(new Activation(T7, "P_10", TransitionOperation.PopElementWithoutTarget, "P_11"));
        grdT7.Activations.add(new Activation(T7, "P_TL16", TransitionOperation.Move, "P_TL16"));

        T7.GuardMappingList.add(grdT7);

        petriNet.Transitions.add(T7);

        // T6 -----------------------------------------------------------------------------------

        PetriTransition T6 = new PetriTransition(petriNet);
        T6.TransitionName = "T6";

        T6.InputPlaceName.add("P_11");
        T6.InputPlaceName.add("P_5");

        Condition T6Ct1 = new Condition(T6, "P_11", TransitionCondition.NotNull);
        Condition T6Ct2 = new Condition(T6, "P_5", TransitionCondition.CanAddCars);
        T6Ct1.SetNextCondition(LogicConnector.AND, T6Ct2);

        GuardMapping grdT6 = new GuardMapping();
        grdT6.condition = T6Ct1;
        grdT6.Activations.add(new Activation(T6, "P_11", TransitionOperation.AddElement, "P_5"));
        T6.GuardMappingList.add(grdT6);

        T6.Delay = 0;
        petriNet.Transitions.add(T6);

        // Left exit --------------------------------------------------------------------------------
        // T3 ---------------------------------------------------------------------------------------

        PetriTransition T3 = new PetriTransition(petriNet);
        T3.TransitionName = "T3";

        T3.InputPlaceName.add("P_7");
        T3.InputPlaceName.add("P_5");

        Condition T3Ct1 = new Condition(T3, "P_5", TransitionCondition.HaveCarForMe);
        Condition T3Ct2 = new Condition(T3, "P_7", TransitionCondition.CanAddCars);
        T3Ct1.SetNextCondition(LogicConnector.AND, T3Ct2);

        GuardMapping grdT3 = new GuardMapping();
        grdT3.condition = T3Ct1;
        grdT3.Activations.add(new Activation(T3, "P_5", TransitionOperation.PopElementWithTargetToQueue, "P_7"));
        T3.GuardMappingList.add(grdT3);

        T3.Delay = 0;
        petriNet.Transitions.add(T3);

        // T4 ---------------------------------------------------------------------------------------

        PetriTransition T4 = new PetriTransition(petriNet);
        T4.TransitionName = "T4";

        T4.InputPlaceName.add("P_7");

        Condition T4Ct1 = new Condition(T4, "P_7", TransitionCondition.HaveCar);

        GuardMapping grdT4 = new GuardMapping();
        grdT4.condition = T4Ct1;
        grdT4.Activations.add(new Activation(T4, "P_7", TransitionOperation.PopElementWithoutTarget, "P_8"));
        T4.GuardMappingList.add(grdT4);

        T4.Delay = 0;
        petriNet.Transitions.add(T4);

        // Right entrance -------------------------------------------------------------------------
        // T10 ------------------------------------------------------------------------------------

        PetriTransition T10 = new PetriTransition(petriNet);
        T10.TransitionName = "T10";

        T10.InputPlaceName.add("P_17");
        T10.InputPlaceName.add("P_5");

        Condition T10Ct1 = new Condition(T10, "P_17", TransitionCondition.NotNull);
        Condition T10Ct2 = new Condition(T10, "P_5", TransitionCondition.CanAddCars);
        T10Ct1.SetNextCondition(LogicConnector.AND, T10Ct2);

        GuardMapping grdT10 = new GuardMapping();
        grdT10.condition = T10Ct1;
        grdT10.Activations.add(new Activation(T10, "P_17", TransitionOperation.AddElement, "P_5"));
        T10.GuardMappingList.add(grdT10);

        T10.Delay = 0;
        petriNet.Transitions.add(T10);

        // T13 -------------------------------------------------------------------------------------

        PetriTransition T13 = new PetriTransition(petriNet);
        T13.TransitionName = "T13";

        T13.InputPlaceName.add("P_18");
        T13.InputPlaceName.add("P_TL20");

        Condition T13Ct1 = new Condition(T13, "P_TL20", TransitionCondition.Equal, "G");
        Condition T13Ct2 = new Condition(T13, "P_18", TransitionCondition.HaveCar);
        T13Ct1.SetNextCondition(LogicConnector.AND, T13Ct2);

        GuardMapping grdT13 = new GuardMapping();
        grdT13.condition = T13Ct1;
        grdT13.Activations.add(new Activation(T13, "P_18", TransitionOperation.PopElementWithoutTarget, "P_17"));
        grdT13.Activations.add(new Activation(T13, "P_TL20", TransitionOperation.Move, "P_TL20"));

        T13.GuardMappingList.add(grdT13);

        petriNet.Transitions.add(T13);

        // T14 -------------------------------------------------------------------------------------

        PetriTransition T14 = new PetriTransition(petriNet);
        T14.TransitionName = "T14";

        T14.InputPlaceName.add("P_19");
        T14.InputPlaceName.add("P_18");

        Condition T14Ct1 = new Condition(T14, "P_19", TransitionCondition.NotNull);
        Condition T14Ct2 = new Condition(T14, "P_18", TransitionCondition.CanAddCars);
        T14Ct1.SetNextCondition(LogicConnector.AND, T14Ct2);

        GuardMapping grdT14 = new GuardMapping();
        grdT14.condition = T14Ct1;
        grdT14.Activations.add(new Activation(T14, "P_19", TransitionOperation.AddElement, "P_18"));
        T14.GuardMappingList.add(grdT14);

        Condition T14Ct3 = new Condition(T14, "P_19", TransitionCondition.NotNull);
        Condition T14Ct4 = new Condition(T14, "P_18", TransitionCondition.CanNotAddCars);
        T14Ct3.SetNextCondition(LogicConnector.AND, T14Ct4);

        GuardMapping grdT14_2 = new GuardMapping();
        grdT14_2.condition = T14Ct3;
        grdT14_2.Activations.add(new Activation(T14, "full", TransitionOperation.SendOverNetwork, "OP_21"));
        grdT14_2.Activations.add(new Activation(T14, "P_19", TransitionOperation.Move, "P_19"));
        T14.GuardMappingList.add(grdT14_2);

        T14.Delay = 0;
        petriNet.Transitions.add(T14);

        // Right exit --------------------------------------------------------------------------------
        // T11 ---------------------------------------------------------------------------------------

        PetriTransition T11 = new PetriTransition(petriNet);
        T11.TransitionName = "T11";

        T11.InputPlaceName.add("P_22");
        T11.InputPlaceName.add("P_5");

        Condition T11Ct1 = new Condition(T11, "P_5", TransitionCondition.HaveCarForMe);
        Condition T11Ct2 = new Condition(T11, "P_22", TransitionCondition.CanAddCars);
        T11Ct1.SetNextCondition(LogicConnector.AND, T11Ct2);

        GuardMapping grdT11 = new GuardMapping();
        grdT11.condition = T11Ct1;
        grdT11.Activations.add(new Activation(T11, "P_5", TransitionOperation.PopElementWithTargetToQueue, "P_22"));
        T11.GuardMappingList.add(grdT11);

        T11.Delay = 0;
        petriNet.Transitions.add(T11);

        // T12 ---------------------------------------------------------------------------------------

        PetriTransition T12 = new PetriTransition(petriNet);
        T12.TransitionName = "T12";

        T12.InputPlaceName.add("P_22");

        Condition T12Ct1 = new Condition(T12, "P_22", TransitionCondition.HaveCar);

        GuardMapping grdT12 = new GuardMapping();
        grdT12.condition = T12Ct1;
        grdT12.Activations.add(new Activation(T12, "P_22", TransitionOperation.PopElementWithoutTarget, "P_23"));
        T12.GuardMappingList.add(grdT12);

        T12.Delay = 0;
        petriNet.Transitions.add(T12);

        // Lower exit --------------------------------------------------------------------------------
        // T5 ---------------------------------------------------------------------------------------

        PetriTransition T5 = new PetriTransition(petriNet);
        T5.TransitionName = "T5";

        T5.InputPlaceName.add("P_6");
        T5.InputPlaceName.add("P_5");

        Condition T5Ct1 = new Condition(T5, "P_5", TransitionCondition.HaveCarForMe);
        Condition T5Ct2 = new Condition(T5, "P_6", TransitionCondition.CanAddCars);
        T5Ct1.SetNextCondition(LogicConnector.AND, T5Ct2);

        GuardMapping grdT5 = new GuardMapping();
        grdT5.condition = T5Ct1;
        grdT5.Activations.add(new Activation(T5, "P_5", TransitionOperation.PopElementWithTargetToQueue, "P_6"));
        T5.GuardMappingList.add(grdT5);

        T5.Delay = 0;
        petriNet.Transitions.add(T5);

        // T9 ---------------------------------------------------------------------------------------

        PetriTransition T9 = new PetriTransition(petriNet);
        T9.TransitionName = "T9";

        T9.InputPlaceName.add("P_6");

        Condition T9Ct1 = new Condition(T9, "P_6", TransitionCondition.HaveCar);

        GuardMapping grdT9 = new GuardMapping();
        grdT9.condition = T9Ct1;
        grdT9.Activations.add(new Activation(T9, "P_6", TransitionOperation.PopElementWithoutTarget, "P_30"));
        T9.GuardMappingList.add(grdT9);

        T9.Delay = 0;
        petriNet.Transitions.add(T9);

        // T_street1 ---------------------------------------------------------------------------------------

        PetriTransition T_street1 = new PetriTransition(petriNet);
        T_street1.TransitionName = "T_street1";

        T_street1.InputPlaceName.add("P_30");

        Condition T_street1Ct1 = new Condition(T_street1, "P_30", TransitionCondition.NotNull);

        GuardMapping grdT_street1 = new GuardMapping();
        grdT_street1.condition = T_street1Ct1;
        grdT_street1.Activations.add(new Activation(T_street1, "P_30", TransitionOperation.SendOverNetwork, "P_street1"));

        T_street1.GuardMappingList.add(grdT_street1);
        T_street1.Delay = 0;
        petriNet.Transitions.add(T_street1);

//        // T_street2 ---------------------------------------------------------------------------------------
//
//        PetriTransition T_street2 = new PetriTransition(petriNet);
//        T_street2.TransitionName = "T_street2";
//
//        T_street2.InputPlaceName.add("P_street1");
//
//        Condition T_street2Ct1 = new Condition(T_street2, "P_street1", TransitionCondition.NotNull);
//
//        GuardMapping grdT_street2 = new GuardMapping();
//        grdT_street2.condition = T_street2Ct1;
//        grdT_street2.Activations.add(new Activation(T_street2, "P_street1", TransitionOperation.SendOverNetwork, "P_street2"));
//
//        T_street2.GuardMappingList.add(grdT_street2);
//        T_street2.Delay = 0;
//        petriNet.Transitions.add(T_street2);

        // -------------------------------------------------------------------------------------------
        // ----------------------------PNStart--------------------------------------------------------
        // -------------------------------------------------------------------------------------------

        System.out.println("Exp1 started \n ------------------------------");
        petriNet.Delay = 2000;
        // petriNet.Start();

        PetriNetWindow frame = new PetriNetWindow(false);
        frame.petriNet = petriNet;
        frame.setVisible(true);

    }

}
