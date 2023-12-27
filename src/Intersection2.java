import Components.*;
import DataObjects.DataCar;
import DataObjects.DataCarQueue;
import DataObjects.DataTransfer;
import DataOnly.TransferOperation;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

public class Intersection2 {
    public static void main(String[] args)
    {
        PetriNet pn = new PetriNet();
        pn.PetriNetName = "Controller 2";

        pn.NetworkPort = 1081;

        // ------------------------------------------------------------------------
        // -------------------------------Left Lane--------------------------------
        // ------------------------------------------------------------------------

        DataCar p10 = new DataCar();
        p10.SetName("P10");
        pn.PlaceList.add(p10);

        // -------------------------------------------------------------------------
        // -------------------------------Right Lane--------------------------------
        // -------------------------------------------------------------------------

        DataCarQueue p11 = new DataCarQueue();
        p11.SetName("P11");
        pn.PlaceList.add(p11);

        DataCar p12 = new DataCar();
        p12.SetName("P12");
        pn.PlaceList.add(p12);

        // ---------------------------------------------------------------------------
        // -------------------------------Lower Lane----------------------------------
        // ---------------------------------------------------------------------------

        DataCarQueue p4 = new DataCarQueue();
        p4.SetName("P4");
        pn.PlaceList.add(p4);

        // -----------------------------------------------------------------------------
        // -------------------------------Intersection----------------------------------
        // -----------------------------------------------------------------------------

        DataCarQueue p3 = new DataCarQueue();
        p3.SetName("P3");
        pn.PlaceList.add(p3);

        // T10 ------------------------------------------------
        PetriTransition t10 = new PetriTransition(pn);
        t10.TransitionName = "T10";
        t10.InputPlaceName.add("P10");

        Condition T10Ct0 = new Condition(t10, "P10", TransitionCondition.NotNull);
        Condition T10Ct1 = new Condition(t10, "P3", TransitionCondition.CanAddCars);
        T10Ct0.SetNextCondition(LogicConnector.AND, T10Ct1);

        GuardMapping grdT10 = new GuardMapping();
        grdT10.condition= T10Ct0;
        grdT10.Activations.add(new Activation(t10, "P10", TransitionOperation.AddElement, "P3"));

        pn.Transitions.add(t10);

        // T12 ------------------------------------------------
        PetriTransition t12 = new PetriTransition(pn);
        t12.TransitionName = "T12";
        t12.InputPlaceName.add("P12");

        Condition T12Ct0 = new Condition(t12, "P12", TransitionCondition.NotNull);
        Condition T12Ct1 = new Condition(t12, "P3", TransitionCondition.CanAddCars);
        T12Ct0.SetNextCondition(LogicConnector.AND, T12Ct1);

        GuardMapping grdT12 = new GuardMapping();
        grdT12.condition= T12Ct0;
        grdT12.Activations.add(new Activation(t12, "P12", TransitionOperation.AddElement, "P3"));

        pn.Transitions.add(t12);

        // T11 ------------------------------------------------
        PetriTransition t11 = new PetriTransition(pn);
        t11.TransitionName = "T11";
        t11.InputPlaceName.add("P3");

        Condition T11Ct0 = new Condition(t11, "P3", TransitionCondition.NotNull);
        Condition T11Ct1 = new Condition(t11, "P11", TransitionCondition.CanAddCars);
        T11Ct0.SetNextCondition(LogicConnector.AND, T11Ct1);

        GuardMapping grdT11 = new GuardMapping();
        grdT11.condition= T11Ct0;
        grdT11.Activations.add(new Activation(t11, "P3", TransitionOperation.AddElement, "P11"));

        pn.Transitions.add(t11);

        // T3 ------------------------------------------------
        PetriTransition t3 = new PetriTransition(pn);
        t3.TransitionName = "T3";
        t3.InputPlaceName.add("P3");

        Condition T3Ct0 = new Condition(t3, "P3", TransitionCondition.NotNull);
        Condition T3Ct1 = new Condition(t3, "P4", TransitionCondition.CanAddCars);
        T3Ct0.SetNextCondition(LogicConnector.AND, T3Ct1);

        GuardMapping grdT3 = new GuardMapping();
        grdT3.condition= T3Ct0;
        grdT3.Activations.add(new Activation(t3, "P3", TransitionOperation.AddElement, "P4"));

        pn.Transitions.add(t3);

        // -------------------------------------------------------------------------------------
        // ----------------------------PNStart-------------------------------------------------
        // -------------------------------------------------------------------------------------

        System.out.println("Exp1 started \n ------------------------------");
        pn.Delay = 2000;
        // pn.Start();

        PetriNetWindow frame = new PetriNetWindow(false);
        frame.petriNet = pn;
        frame.setVisible(true);
    }
}
