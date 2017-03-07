package demo.jxmapviewerdemo;

public class Pre_processing {
	
	//[cities]->[latitude][longitude]
	static double [][] cities = new double[15][2];
      public static void set_city(){
        //First, we take the Zurich coordinates
        double latitude_zurich = 47.458333;
        double longitude_zurich = 8.548056;
        cities[0][0] = latitude_zurich;
        cities[0][1] = longitude_zurich;

        //First, we take the Konstanz coordinates
        double latitude_konstanz = 47.678222;
        double longitude_konstanz = 9.188175;     
        cities[1][0] = latitude_konstanz;
        cities[1][1] = longitude_konstanz;
        
        //First, we take the Nurnberg coordinates
        double latitude_nurnberg = 49.425561;
        double longitude_nurnberg = 11.079746;
        cities[2][0] = latitude_nurnberg;
        cities[2][1] = longitude_nurnberg;

        //First, we take the Donaueschingen coordinates
        double latitude_donaueschingen = 47.951892;
        double longitude_donaueschingen = 8.492422;
        cities[3][0] = latitude_donaueschingen;
        cities[3][1] = longitude_donaueschingen;
        
        //First, we take the Zimmern ob Rottweil coordinates
        double latitude_zimmern = 48.169063;
        double longitude_zimmern = 8.591350; 
        cities[4][0] = latitude_zimmern;
        cities[4][1] = longitude_zimmern;
        
        //First, we take the Pforzheim coordinates
        double latitude_pforzheim = 48.892849;
        double longitude_pforzheim = 8.702348; 
        cities[5][0] = latitude_pforzheim;
        cities[5][1] = longitude_pforzheim;
        
        //First, we take the Waldshut-Tiengen coordinates
        double latitude_waldshut = 47.630887;
        double longitude_waldshut = 8.269858;
        cities[6][0] = latitude_waldshut;
        cities[6][1] = longitude_waldshut;
        
        //First, we take the Kempten coordinates
        double latitude_kempten = 47.737093;
        double longitude_kempten = 10.342187;
        cities[7][0] = latitude_kempten;
        cities[7][1] = longitude_kempten;
        
        //First, we take the Sulz am Neckar coordinates
        double latitude_sulz = 48.354679;
        double longitude_sulz = 8.631490;
        cities[8][0] = latitude_sulz;
        cities[8][1] = longitude_sulz;
}
}