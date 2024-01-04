package Intersection1;

import Components.*;
import DataObjects.DataCar;
import DataObjects.DataCarQueue;
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
        pn.PetriNetName = "Controller 1";

        pn.NetworkPort = 1080;

        // ------------------------------------------------------------------------
        // -------------------------------Left Lane--------------------------------
        // ------------------------------------------------------------------------

        DataCar p5 = new DataCar();
        p5.SetName("P5");
        pn.PlaceList.add(p5);

        DataCarQueue p6 = new DataCarQueue();
        p6.SetName("P6");
        pn.PlaceList.add(p6);

        // -------------------------------------------------------------------------
        // -------------------------------Right Lane--------------------------------
        // -------------------------------------------------------------------------

        DataCarQueue p7 = new DataCarQueue();
        p7.SetName("P7");
        pn.PlaceList.add(p7);

        DataCar p8 = new DataCar();
        p8.SetName("P8");
        pn.PlaceList.add(p8);

        // ---------------------------------------------------------------------------
        // -------------------------------Upper Lane----------------------------------
        // ---------------------------------------------------------------------------

        DataCar p0 = new DataCar();
        p0.SetName("P0");
        pn.PlaceList.add(p0);

        // -----------------------------------------------------------------------------
        // -------------------------------Intersection----------------------------------
        // -----------------------------------------------------------------------------
        DataCarQueue p1 = new DataCarQueue();
        p1.SetName("P1");
        pn.PlaceList.add(p1);

        // Implementing P2 as an output channel from controller 1 to controller 2
        DataTransfer p2 = new DataTransfer();
        p2.SetName("P2");
        p2.Value = new TransferOperation("localhost", "1081", "p3");
        pn.PlaceList.add(p2);

        // First way of access to the intersection from the split of the road
        DataCar p9 = new DataCar();
        p9.SetName("P9");
        pn.PlaceList.add(p9);

        // T5 ------------------------------------------------
        PetriTransition t5 = new PetriTransition(pn);
        t5.TransitionName = "T5";
        t5.InputPlaceName.add("P5");

        Condition T5Ct0 = new Condition(t5, "P5", TransitionCondition.NotNull);
        Condition T5Ct1 = new Condition(t5, "P1", TransitionCondition.CanAddCars);
        T5Ct0.SetNextCondition(LogicConnector.AND, T5Ct1);

        GuardMapping grdT5 = new GuardMapping();
        grdT5.condition = T5Ct0;
        grdT5.Activations.add(new Activation(t5, "P5", TransitionOperation.AddElement, "P1"));

        pn.Transitions.add(t5);

        // T6 ------------------------------------------------
        PetriTransition t6 = new PetriTransition(pn);
        t6.TransitionName = "T6";
        t6.InputPlaceName.add("P1");

        Condition T6Ct0 = new Condition(t6, "P1", TransitionCondition.NotNull);
        Condition T6Ct1 = new Condition(t6, "P6", TransitionCondition.CanAddCars);
        T6Ct0.SetNextCondition(LogicConnector.AND, T6Ct1);

        GuardMapping grdT6 = new GuardMapping();
        grdT6.condition = T6Ct0;
        grdT6.Activations.add(new Activation(t6, "P1", TransitionOperation.AddElement, "P6"));

        pn.Transitions.add(t6);

        // T7 ------------------------------------------------
        PetriTransition t7 = new PetriTransition(pn);
        t7.TransitionName = "T7";
        t7.InputPlaceName.add("P1");

        Condition T7Ct0 = new Condition(t7, "P1", TransitionCondition.NotNull);
        Condition T7Ct1 = new Condition(t7, "P7", TransitionCondition.CanAddCars);
        T7Ct0.SetNextCondition(LogicConnector.AND, T7Ct1);

        GuardMapping grdT7 = new GuardMapping();
        grdT7.condition = T7Ct0;
        grdT7.Activations.add(new Activation(t7, "P1", TransitionOperation.AddElement, "P7"));

        pn.Transitions.add(t7);

        // T8 ------------------------------------------------
        PetriTransition t8 = new PetriTransition(pn);
        t8.TransitionName = "T8";
        t8.InputPlaceName.add("P8");

        Condition T8Ct0 = new Condition(t8, "P8", TransitionCondition.NotNull);
        Condition T8Ct1 = new Condition(t8, "P1", TransitionCondition.CanAddCars);
        T8Ct0.SetNextCondition(LogicConnector.AND, T8Ct1);

        GuardMapping grdT8 = new GuardMapping();
        grdT8.condition = T8Ct0;
        grdT8.Activations.add(new Activation(t8, "P8", TransitionOperation.AddElement, "P1"));

        pn.Transitions.add(t8);

        // T0 ------------------------------------------------
        PetriTransition t0 = new PetriTransition(pn);
        t0.TransitionName = "T0";
        t0.InputPlaceName.add("P0");

        Condition T0Ct0 = new Condition(t0, "P0", TransitionCondition.NotNull);
        Condition T0Ct1 = new Condition(t0, "P1", TransitionCondition.CanAddCars);
        T0Ct0.SetNextCondition(LogicConnector.AND, T0Ct1);

        GuardMapping grdT0 = new GuardMapping();
        grdT0.condition = T0Ct0;
        grdT0.Activations.add(new Activation(t0, "P0", TransitionOperation.AddElement, "P1"));

        pn.Transitions.add(t0);

        // T1 ------------------------------------------------
        PetriTransition t1 = new PetriTransition(pn);
        t1.TransitionName = "T1";
        t1.InputPlaceName.add("P1");

        Condition T1Ct0 = new Condition(t1, "P1", TransitionCondition.NotNull);
        Condition T1Ct1 = new Condition(t1, "P2", TransitionCondition.CanAddCars);
        T1Ct0.SetNextCondition(LogicConnector.AND, T1Ct1);

        GuardMapping grdT1 = new GuardMapping();
        grdT1.condition = T1Ct0;
        grdT1.Activations.add(new Activation(t1, "P1", TransitionOperation.AddElement, "P2"));

        pn.Transitions.add(t1);

        // T9 ------------------------------------------------
        PetriTransition t9 = new PetriTransition(pn);
        t9.TransitionName = "T9";
        t9.InputPlaceName.add("P9");

        Condition T9Ct0 = new Condition(t9, "P9", TransitionCondition.NotNull);
        Condition T9Ct1 = new Condition(t9, "P2", TransitionCondition.CanAddCars);
        T9Ct0.SetNextCondition(LogicConnector.AND, T9Ct1);

        GuardMapping grdT9 = new GuardMapping();
        grdT9.condition = T9Ct0;
        grdT9.Activations.add(new Activation(t9, "P9", TransitionOperation.AddElement, "P2"));

        pn.Transitions.add(t9);

        // -------------------------------------------------------------------------------------
        // ----------------------------PNStart-------------------------------------------------
        // -------------------------------------------------------------------------------------

        System.out.println("Exp1 started \n ------------------------------");
        pn.Delay = 2000;
        // pn.Start();

        PetriNetWindow frame = new PetriNetWindow(false);
        frame.petriNet = pn;
        frame.setVisible(true);

        // Intersection2.Intersection2.main(args); // Start the second controller
        InputCar.main(args); // Start the input car GUI
    }
}