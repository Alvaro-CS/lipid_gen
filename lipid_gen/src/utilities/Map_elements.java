package utilities;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.Element;

public class Map_elements {
	
	

	public static final Map<Element, Double> MAPELEMENTS = new HashMap<Element, Double>();

    static {

        MAPELEMENTS.put(Element.H, 1.007825d);
        MAPELEMENTS.put(Element.He, 4.002603d);//comprobar si lo hacemos as� al ser enum
        /*
        MAPELEMENTS.put("Li", 7.016005d);
        MAPELEMENTS.put("Be", 9.012183d);
        MAPELEMENTS.put("B", 11.009305d);
        MAPELEMENTS.put("C", 12d);
        MAPELEMENTS.put("N", 14.003074d);
        MAPELEMENTS.put("O", 15.994915d);
        MAPELEMENTS.put("F", 18.998403d);
        MAPELEMENTS.put("Ne", 19.992439d);
        MAPELEMENTS.put("Na", 22.989770d);
        MAPELEMENTS.put("Mg", 23.985045d);
        MAPELEMENTS.put("Al", 26.981541d);
        MAPELEMENTS.put("Si", 27.976928d);
        MAPELEMENTS.put("P", 30.973763d);
        MAPELEMENTS.put("S", 31.972072d);
        MAPELEMENTS.put("Cl", 34.968853d);
        MAPELEMENTS.put("Ar", 39.962383d);
        MAPELEMENTS.put("K", 38.963708d);
        MAPELEMENTS.put("Ca", 39.962591d);
        MAPELEMENTS.put("Sc", 44.955914d);
        MAPELEMENTS.put("Ti", 47.947947d);
        MAPELEMENTS.put("V", 50.943963d);
        MAPELEMENTS.put("Cr", 51.940510d);
        MAPELEMENTS.put("Mn", 54.938046d);
        MAPELEMENTS.put("Fe", 55.934939d);
        MAPELEMENTS.put("Ni", 57.935347d);
        MAPELEMENTS.put("Co", 58.933198d);
        MAPELEMENTS.put("Cu", 62.929599d);
        MAPELEMENTS.put("Zn", 63.929145d);
        MAPELEMENTS.put("Ga", 68.925581d);
        MAPELEMENTS.put("Ge", 73.921179d);
        MAPELEMENTS.put("As", 74.921596d);
        MAPELEMENTS.put("Se", 79.916521d);
        MAPELEMENTS.put("Br", 78.918336d);
        MAPELEMENTS.put("Kr", 83.911506d);
        MAPELEMENTS.put("Rb", 84.911800d);
        MAPELEMENTS.put("Sr", 87.905625d);
        MAPELEMENTS.put("Y", 88.905856d);
        MAPELEMENTS.put("Zr", 89.904708d);
        MAPELEMENTS.put("Nb", 92.906378d);
        MAPELEMENTS.put("Mo", 97.905405d);
        MAPELEMENTS.put("Tc", 98.0d); //Not in webpage
        MAPELEMENTS.put("Ru", 101.904348d);
        MAPELEMENTS.put("Rh", 102.905503d);
        MAPELEMENTS.put("Pd", 105.903475d);
        MAPELEMENTS.put("Ag", 106.905095d);
        MAPELEMENTS.put("Cd", 113.903361d);
        MAPELEMENTS.put("In", 114.903875d);
        MAPELEMENTS.put("Sn", 118.710d);
        MAPELEMENTS.put("Sb", 120.903824d);
        MAPELEMENTS.put("Te", 129.906229d);
        MAPELEMENTS.put("I", 126.904477d);
        MAPELEMENTS.put("Xe", 131.293d);
        MAPELEMENTS.put("Cs", 132.905433d);
        MAPELEMENTS.put("Ba", 137.905236d);
        //From here, mass not updated
        MAPELEMENTS.put("La", 138.90547d);
        MAPELEMENTS.put("Ce", 140.116d);
        MAPELEMENTS.put("Pr", 140.90766d);
        MAPELEMENTS.put("Nd", 144.242d);
        MAPELEMENTS.put("Pm", 145.0d);
        MAPELEMENTS.put("Sm", 150.36d);
        MAPELEMENTS.put("Eu", 151.964d);
        MAPELEMENTS.put("Gd", 157.25d);
        MAPELEMENTS.put("Tb", 158.92535d);
        MAPELEMENTS.put("Dy", 162.500d);
        MAPELEMENTS.put("Ho", 164.93033d);
        MAPELEMENTS.put("Er", 167.259d);
        MAPELEMENTS.put("Tm", 168.93422d);
        MAPELEMENTS.put("Yb", 173.045d);
        MAPELEMENTS.put("Lu", 173.045d);
        MAPELEMENTS.put("Hf", 178.49d);
        MAPELEMENTS.put("Ta", 180.94788d);
        MAPELEMENTS.put("W", 183.84d);
        MAPELEMENTS.put("Re", 186.207d);
        MAPELEMENTS.put("Os", 190.23d);
        MAPELEMENTS.put("Ir", 192.217d);
        MAPELEMENTS.put("Pt", 195.084d);
        MAPELEMENTS.put("Au", 196.966569d);
        MAPELEMENTS.put("Hg", 200.592d);
        MAPELEMENTS.put("Tl", 204.38d);
        MAPELEMENTS.put("Pb", 207.2d);
        MAPELEMENTS.put("Bi", 208.98040d);
        MAPELEMENTS.put("Po", 209.0d);
        MAPELEMENTS.put("At", 210.0d);
        MAPELEMENTS.put("Rn", 222.0d);
        MAPELEMENTS.put("Fr", 223.0d);
        MAPELEMENTS.put("Ra", 226.0d);
        MAPELEMENTS.put("Ac", 227.0d);
        MAPELEMENTS.put("Th", 232.0377d);
        MAPELEMENTS.put("Pa", 231.03588d);
        MAPELEMENTS.put("U", 238.02891d);
        MAPELEMENTS.put("Np", 237.0d);
        MAPELEMENTS.put("Pu", 244.0d);
        MAPELEMENTS.put("Am", 243.0d);
        MAPELEMENTS.put("Cm", 247.0d);
        MAPELEMENTS.put("Bk", 247.0d);
        MAPELEMENTS.put("Cf", 251.0d);
        MAPELEMENTS.put("Es", 252.0d);
        MAPELEMENTS.put("Fm", 257.0d);
        MAPELEMENTS.put("Md", 258.0d);
        MAPELEMENTS.put("No", 259.0d);
        MAPELEMENTS.put("Lr", 266.0d);
        MAPELEMENTS.put("Rf", 267.0d);
        MAPELEMENTS.put("Db", 268.0d);
        MAPELEMENTS.put("Sg", 269.0d);
        MAPELEMENTS.put("Bh", 270.0d);
        MAPELEMENTS.put("Hs", 269.0d);
        MAPELEMENTS.put("Mt", 278.0d);
        MAPELEMENTS.put("Ds", 281.0d);
        MAPELEMENTS.put("Rg", 282.0d);
        MAPELEMENTS.put("Cn", 285.0d);
        MAPELEMENTS.put("Uut", 286.0d);
        MAPELEMENTS.put("Fl", 289.0d);
        MAPELEMENTS.put("Uup", 289.0d);
        MAPELEMENTS.put("Lv", 293.0d);
        MAPELEMENTS.put("Uus", 294.0d);
        MAPELEMENTS.put("Uuo", 294.0d);
        */
    }

}
