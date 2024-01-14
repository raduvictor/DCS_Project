package Intersection2;

import Components.*;
import DataObjects.DataCar;
import DataObjects.DataCarQueue;
import DataObjects.DataString;
import DataObjects.DataTransfer;
import DataOnly.TransferOperation;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

public class Intersection2 {
    public static void main(String[] args) {
        PetriNet petriNet = new PetriNet();
        petriNet.PetriNetName = "Intersection2";

        petriNet.NetworkPort = 1081;

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
        DataCarQueue p42 = new DataCarQueue();
        p42.Value.Size = 3;
        p42.SetName("P_42");
        petriNet.PlaceList.add(p42);


        // --------------------------------------------------------------------------------------
        // ---------------------------- Upper part of the intersection --------------------------
        // --------------------------------------------------------------------------------------
        DataCar P_street2 = new DataCar();
        P_street2.SetName("P_street2");
        petriNet.PlaceList.add(P_street2);

        DataTransfer OP31 = new DataTransfer();
        OP31.SetName("OP_31");
        petriNet.PlaceList.add(OP31);
        OP31.Value = new TransferOperation("localhost", "1083", "in");

        DataCarQueue P32 = new DataCarQueue();
        P32.Value.Size = 3;
        P32.SetName("P_32");
        petriNet.PlaceList.add(P32);

        DataString P_TL33 = new DataString();
        P_TL33.SetName("P_TL33");
//        P_TL33.SetValue("red");
        petriNet.PlaceList.add(P_TL33);

        DataCar P34 = new DataCar();
        P34.SetName("P_34");
        petriNet.PlaceList.add(P34);


        // --------------------------------------------------------------------------------------
        // ---------------------------- Left part of the intersection --------------------------
        // --------------------------------------------------------------------------------------

        DataCar P43 = new DataCar();
        P43.SetName("P_43");
        petriNet.PlaceList.add(P43);

        DataString P_TL44 = new DataString();
        P_TL44.SetName("P_TL44");
//        P_TL44.SetValue("red");
        petriNet.PlaceList.add(P_TL44);

        DataCarQueue P44 = new DataCarQueue();
        P44.Value.Size = 3;
        P44.SetName("P_44");
        petriNet.PlaceList.add(P44);

        DataTransfer OP45 = new DataTransfer();
        OP45.SetName("OP_45");
        petriNet.PlaceList.add(OP45);
        OP45.Value = new TransferOperation("localhost", "1083", "in");

        DataCar P46 = new DataCar();
        P46.SetName("P_46");
        petriNet.PlaceList.add(P46);

        // --------------------------------------------------------------------------------------
        // ---------------------------- Right part of the intersection --------------------------
        // --------------------------------------------------------------------------------------

        // UP
        DataCar P39 = new DataCar();
        P39.SetName("P_39");
        petriNet.PlaceList.add(P39);

        DataString P_TL35 = new DataString();
        P_TL35.SetName("P_TL35");
//        P_TL35.SetValue("red");
        petriNet.PlaceList.add(P_TL35);

        DataCarQueue P38 = new DataCarQueue();
        P38.Value.Size = 3;
        P38.SetName("P_38");
        petriNet.PlaceList.add(P38);

        DataTransfer OP36 = new DataTransfer();
        OP36.SetName("OP_36");
        petriNet.PlaceList.add(OP36);
        OP36.Value = new TransferOperation("localhost", "1083", "in");

        // DOWN
        DataCarQueue P40 = new DataCarQueue();
        P40.Value.Size = 3;
        P40.SetName("P_40");
        petriNet.PlaceList.add(P40);

        DataCar P41 = new DataCar();
        P41.SetName("P_41");
        petriNet.PlaceList.add(P41);

        // --------------------------------------------------------------------------------------
        // ---------------------------- Lower part of the intersection --------------------------
        // --------------------------------------------------------------------------------------

        DataCarQueue P50 = new DataCarQueue();
        P50.Value.Size = 3;
        P50.SetName("P_50");
        petriNet.PlaceList.add(P50);

        DataCar P47 = new DataCar();
        P47.SetName("P_47");
        petriNet.PlaceList.add(P47);


        // ---------------------------------------------------------------------------------------
        // ---------------------------- Transitions ----------------------------------------------
        // ---------------------------------------------------------------------------------------

        // Upper entrance -------------------------------------------------------------------------
        // T30 -----------------------------------------------------------------------------------

        PetriTransition T30 = new PetriTransition(petriNet);
        T30.TransitionName = "T30";

        T30.InputPlaceName.add("P_street2");
        T30.InputPlaceName.add("P_32");

        Condition T30Ct1 = new Condition(T30, "P_street2", TransitionCondition.NotNull);
        Condition T30Ct2 = new Condition(T30, "P_32", TransitionCondition.CanAddCars);
        T30Ct1.SetNextCondition(LogicConnector.AND, T30Ct2);

        GuardMapping grdT30 = new GuardMapping();
        grdT30.condition = T30Ct1;
        grdT30.Activations.add(new Activation(T30, "P_street2", TransitionOperation.AddElement, "P_32"));
        T30.GuardMappingList.add(grdT30);

        Condition T30Ct3 = new Condition(T30, "P_street2", TransitionCondition.NotNull);
        Condition T30Ct4 = new Condition(T30, "P_32", TransitionCondition.CanNotAddCars);
        T30Ct3.SetNextCondition(LogicConnector.AND, T30Ct4);

        GuardMapping grdT30_2 = new GuardMapping();
        grdT30_2.condition = T30Ct3;
        grdT30_2.Activations.add(new Activation(T30, "full", TransitionOperation.SendOverNetwork, "OP_31"));
        grdT30_2.Activations.add(new Activation(T30, "P_street2", TransitionOperation.Move, "P_street2"));
        T30.GuardMappingList.add(grdT30_2);

        T30.Delay = 0;
        petriNet.Transitions.add(T30);

        // T31 -----------------------------------------------------------------------------------

        PetriTransition T31 = new PetriTransition(petriNet);
        T31.TransitionName = "T31";

        T31.InputPlaceName.add("P_32");
        T31.InputPlaceName.add("P_TL33");

        Condition T31Ct1 = new Condition(T31, "P_TL33", TransitionCondition.Equal, "G");
        Condition T31Ct2 = new Condition(T31, "P_32", TransitionCondition.HaveCar);
        T31Ct1.SetNextCondition(LogicConnector.AND, T31Ct2);

        GuardMapping grdT31 = new GuardMapping();
        grdT31.condition = T31Ct1;
        grdT31.Activations.add(new Activation(T31, "P_32", TransitionOperation.PopElementWithoutTarget, "P_34"));
        grdT31.Activations.add(new Activation(T31, "P_TL33", TransitionOperation.Move, "P_TL33"));

        T31.GuardMappingList.add(grdT31);

        petriNet.Transitions.add(T31);

        // T32 -----------------------------------------------------------------------------------

        PetriTransition T32 = new PetriTransition(petriNet);
        T32.TransitionName = "T32";
        T32.InputPlaceName.add("P_34");
        T32.InputPlaceName.add("P_42");

        Condition T32Ct1 = new Condition(T32, "P_34", TransitionCondition.NotNull);
        Condition T32Ct2 = new Condition(T32, "P_42", TransitionCondition.CanAddCars);
        T32Ct1.SetNextCondition(LogicConnector.AND, T32Ct2);

        GuardMapping grdT32 = new GuardMapping();
        grdT32.condition = T32Ct1;
        grdT32.Activations.add(new Activation(T32, "P_34", TransitionOperation.AddElement, "P_42"));
        T32.GuardMappingList.add(grdT32);

        T32.Delay = 0;
        petriNet.Transitions.add(T32);

        // Left entrance -------------------------------------------------------------------------
        // T45 -----------------------------------------------------------------------------------

        PetriTransition T45 = new PetriTransition(petriNet);
        T45.TransitionName = "T45";

        T45.InputPlaceName.add("P_46");
        T45.InputPlaceName.add("P_44");

        Condition T45Ct1 = new Condition(T45, "P_46", TransitionCondition.NotNull);
        Condition T45Ct2 = new Condition(T45, "P_44", TransitionCondition.CanAddCars);
        T45Ct1.SetNextCondition(LogicConnector.AND, T45Ct2);

        GuardMapping grdT45 = new GuardMapping();
        grdT45.condition = T45Ct1;
        grdT45.Activations.add(new Activation(T45, "P_46", TransitionOperation.AddElement, "P_44"));
        T45.GuardMappingList.add(grdT45);

        Condition T45Ct3 = new Condition(T45, "P_46", TransitionCondition.NotNull);
        Condition T45Ct4 = new Condition(T45, "P_44", TransitionCondition.CanNotAddCars);
        T45Ct3.SetNextCondition(LogicConnector.AND, T45Ct4);

        GuardMapping grdT45_2 = new GuardMapping();
        grdT45_2.condition = T45Ct3;
        grdT45_2.Activations.add(new Activation(T45, "full", TransitionOperation.SendOverNetwork, "OP_45"));
        grdT45_2.Activations.add(new Activation(T45, "P_46", TransitionOperation.Move, "P_46"));
        T45.GuardMappingList.add(grdT45_2);

        T45.Delay = 0;
        petriNet.Transitions.add(T45);

        // T43 -----------------------------------------------------------------------------------

        PetriTransition T43 = new PetriTransition(petriNet);
        T43.TransitionName = "T43";

        T43.InputPlaceName.add("P_44");
        T43.InputPlaceName.add("P_TL44");

        Condition T43Ct1 = new Condition(T43, "P_TL44", TransitionCondition.Equal, "G");
        Condition T43Ct2 = new Condition(T43, "P_44", TransitionCondition.HaveCar);
        T43Ct1.SetNextCondition(LogicConnector.AND, T43Ct2);

        GuardMapping grdT43 = new GuardMapping();
        grdT43.condition = T43Ct1;
        grdT43.Activations.add(new Activation(T43, "P_44", TransitionOperation.PopElementWithoutTarget, "P_43"));
        grdT43.Activations.add(new Activation(T43, "P_TL44", TransitionOperation.Move, "P_TL44"));

        T43.GuardMappingList.add(grdT43);

        petriNet.Transitions.add(T43);

        // T34 -----------------------------------------------------------------------------------

        PetriTransition T34 = new PetriTransition(petriNet);
        T34.TransitionName = "T34";

        T34.InputPlaceName.add("P_43");
        T34.InputPlaceName.add("P_42");

        Condition T34Ct1 = new Condition(T34, "P_43", TransitionCondition.NotNull);
        Condition T34Ct2 = new Condition(T34, "P_42", TransitionCondition.CanAddCars);
        T34Ct1.SetNextCondition(LogicConnector.AND, T34Ct2);

        GuardMapping grdT34 = new GuardMapping();
        grdT34.condition = T34Ct1;
        grdT34.Activations.add(new Activation(T34, "P_43", TransitionOperation.AddElement, "P_42"));
        T34.GuardMappingList.add(grdT34);

        T34.Delay = 0;
        petriNet.Transitions.add(T34);

        // Right entrance -------------------------------------------------------------------------
        // T33 ------------------------------------------------------------------------------------

        PetriTransition T33 = new PetriTransition(petriNet);
        T33.TransitionName = "T33";

        T33.InputPlaceName.add("P_39");
        T33.InputPlaceName.add("P_42");

        Condition T33Ct1 = new Condition(T33, "P_39", TransitionCondition.NotNull);
        Condition T33Ct2 = new Condition(T33, "P_42", TransitionCondition.CanAddCars);
        T33Ct1.SetNextCondition(LogicConnector.AND, T33Ct2);

        GuardMapping grdT33 = new GuardMapping();
        grdT33.condition = T33Ct1;
        grdT33.Activations.add(new Activation(T33, "P_39", TransitionOperation.AddElement, "P_42"));
        T33.GuardMappingList.add(grdT33);

        T33.Delay = 0;
        petriNet.Transitions.add(T33);

        // T38 -------------------------------------------------------------------------------------

        PetriTransition T38 = new PetriTransition(petriNet);
        T38.TransitionName = "T38";

        T38.InputPlaceName.add("P_38");
        T38.InputPlaceName.add("P_TL35");

        Condition T38Ct1 = new Condition(T38, "P_TL35", TransitionCondition.Equal, "G");
        Condition T38Ct2 = new Condition(T38, "P_38", TransitionCondition.HaveCar);
        T38Ct1.SetNextCondition(LogicConnector.AND, T38Ct2);

        GuardMapping grdT38 = new GuardMapping();
        grdT38.condition = T38Ct1;
        grdT38.Activations.add(new Activation(T38, "P_38", TransitionOperation.PopElementWithoutTarget, "P_39"));
        grdT38.Activations.add(new Activation(T38, "P_TL35", TransitionOperation.Move, "P_TL35"));

        T38.GuardMappingList.add(grdT38);

        petriNet.Transitions.add(T38);

        // T36 -------------------------------------------------------------------------------------

        PetriTransition T36 = new PetriTransition(petriNet);
        T36.TransitionName = "T36";

        T36.InputPlaceName.add("P_37");
        T36.InputPlaceName.add("P_38");

        Condition T36Ct1 = new Condition(T36, "P_37", TransitionCondition.NotNull);
        Condition T36Ct2 = new Condition(T36, "P_38", TransitionCondition.CanAddCars);
        T36Ct1.SetNextCondition(LogicConnector.AND, T36Ct2);

        GuardMapping grdT36 = new GuardMapping();
        grdT36.condition = T36Ct1;
        grdT36.Activations.add(new Activation(T36, "P_37", TransitionOperation.AddElement, "P_38"));
        T36.GuardMappingList.add(grdT36);

        Condition T36Ct3 = new Condition(T36, "P_37", TransitionCondition.NotNull);
        Condition T36Ct4 = new Condition(T36, "P_38", TransitionCondition.CanNotAddCars);
        T36Ct3.SetNextCondition(LogicConnector.AND, T36Ct4);

        GuardMapping grdT36_2 = new GuardMapping();
        grdT36_2.condition = T36Ct3;
        grdT36_2.Activations.add(new Activation(T36, "full", TransitionOperation.SendOverNetwork, "OP_36"));
        grdT36_2.Activations.add(new Activation(T36, "P_37", TransitionOperation.Move, "P_37"));
        T36.GuardMappingList.add(grdT36_2);

        T36.Delay = 0;
        petriNet.Transitions.add(T36);

        // Right exit --------------------------------------------------------------------------------
        // T39 ---------------------------------------------------------------------------------------

        PetriTransition T39 = new PetriTransition(petriNet);
        T39.TransitionName = "T39";

        T39.InputPlaceName.add("P_40");
        T39.InputPlaceName.add("P_42");

        Condition T39Ct1 = new Condition(T39, "P_42", TransitionCondition.HaveCarForMe);
        Condition T39Ct2 = new Condition(T39, "P_40", TransitionCondition.CanAddCars);
        T39Ct1.SetNextCondition(LogicConnector.AND, T39Ct2);

        GuardMapping grdT39 = new GuardMapping();
        grdT39.condition = T39Ct1;
        grdT39.Activations.add(new Activation(T39, "P_42", TransitionOperation.PopElementWithoutTarget, "P_40"));
        T39.GuardMappingList.add(grdT39);

        T39.Delay = 0;
        petriNet.Transitions.add(T39);

        // T40 ---------------------------------------------------------------------------------------

        PetriTransition T40 = new PetriTransition(petriNet);
        T40.TransitionName = "T40";

        T40.InputPlaceName.add("P_40");

        Condition T40Ct1 = new Condition(T40, "P_40", TransitionCondition.HaveCar);

        GuardMapping grdT40 = new GuardMapping();
        grdT40.condition = T40Ct1;
        grdT40.Activations.add(new Activation(T40, "P_40", TransitionOperation.PopElementWithoutTarget, "P_41"));
        T40.GuardMappingList.add(grdT40);

        T40.Delay = 0;
        petriNet.Transitions.add(T40);

        // Lower exit --------------------------------------------------------------------------------
        // T42 ---------------------------------------------------------------------------------------

        PetriTransition T42 = new PetriTransition(petriNet);
        T42.TransitionName = "T42";

        T42.InputPlaceName.add("P_50");
        T42.InputPlaceName.add("P_42");

        Condition T42Ct1 = new Condition(T42, "P_42", TransitionCondition.HaveCarForMe);
        Condition T42Ct2 = new Condition(T42, "P_50", TransitionCondition.CanAddCars);
        T42Ct1.SetNextCondition(LogicConnector.AND, T42Ct2);

        GuardMapping grdT42 = new GuardMapping();
        grdT42.condition = T42Ct1;
        grdT42.Activations.add(new Activation(T42, "P_42", TransitionOperation.PopElementWithoutTarget, "P_50"));
        T42.GuardMappingList.add(grdT42);

        T42.Delay = 0;
        petriNet.Transitions.add(T42);

        // T29 ---------------------------------------------------------------------------------------

        PetriTransition T29 = new PetriTransition(petriNet);
        T29.TransitionName = "T29";

        T29.InputPlaceName.add("P50");

        Condition T29Ct1 = new Condition(T29, "P_50", TransitionCondition.HaveCar);

        GuardMapping grdT29 = new GuardMapping();
        grdT29.condition = T29Ct1;
        grdT29.Activations.add(new Activation(T29, "P_50", TransitionOperation.PopElementWithoutTarget, "P_47"));
        T29.GuardMappingList.add(grdT29);

        T29.Delay = 0;
        petriNet.Transitions.add(T29);

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
