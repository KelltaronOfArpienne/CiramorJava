package View;

public class Hero {
    private static int proximoNumero = 1;   
    public int numero;
    public String nome;
    public String tipo;
    public String guilda;
    public String classe;
    public String raca;
    public String set;
    public String bone;
    public String btwo;
    public String bthree;
    public String mone;
    public String mtwo;
    public String mthree;
    public String tone;
    public String ttwo;
    public String tthree;
    public String special1;
    public String special2;
    public String special3;
    public String abyType1;
    public String abyType2;
    public String abyType3;
    

    public Hero(String nome, String tipo, String guilda, String classe, String raca, String set, String bone, String btwo, String bthree, String mone, String mtwo, String mthree, String tone, String ttwo, String tthree, String special1,String special2,String special3,String abyType1,String abyType2,String abyType3) {
        this.nome = nome;
        this.tipo = tipo;
        this.guilda = guilda;
        this.classe = classe;
        this.raca = raca;
        this.set = set;
        this.numero = Hero.proximoNumero;
        this.bone = bone;
        this.btwo = btwo;
        this.bthree = bthree;
        this.mone = mone;
        this.mtwo = mtwo;
        this.mthree = mthree;
        this.tone = tone;
        this.ttwo = ttwo;
        this.tthree = tthree;
        this.special1 = special1;
        this.special2 = special2;
        this.special3 = special3;
        this.abyType1 = abyType1;
        this.abyType2 = abyType2;
        this.abyType3 = abyType3;
        Hero.proximoNumero++;
    }
}
//Name_Tooltip,Card_Type,Guild,Class,Race,SetCode
