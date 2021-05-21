package Logic;

public class Priser {
        private static Priser instance = null;

        private Priser(){}

        public static Priser i() {
                if (instance == null)
                        instance = new Priser();
                return instance;
        }
        /*
         "Ferrari 812 Superfast 6,5 DCT 2d",
                "Ferrari California T 3,8 F1 2d" ,
                "Ferrari GTC4Lusso T 3,9 DCT 2d",
                "Ferrari Portofino 3,9 DCT 2d",
                "Ferrari 488 Pista 3,9 DCT 2d",
                "Ferrari 488 Spider 3,9 DCT 2d");
         */

         double superfast = 1902300;
         double california = 746000;
         double gtc4 = 1119000;
         double portifino = 1566600;
         double pista = 1344995;
         double spider = 1225000;

        public double getPrice(String bil) {
                double i = 0;
                switch (bil) {
                        case "superfast":
                                i = superfast;
                                break;
                        case "california":
                                i = california;
                                break;
                        case "gtc4":
                                i = gtc4;
                                break;
                        case "portifino":
                                i = portifino;
                                break;
                        case "pista":
                                i = pista;
                                break;
                        case "spider":
                                i = spider;
                                break;
                        case "       ":
                                i = 7;
                                break;
                }
                return i;
        }
}
