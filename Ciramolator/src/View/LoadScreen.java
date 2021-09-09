package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class LoadScreen extends javax.swing.JFrame {

    String setChosen = "";
    String road;
    static List<Hero> heroList;
    JLabel[] labels;
//    String[] checkRaces = {"Beast", "Dais", "Demon", "Dragon", "Ice Elf", "Elfine", "Eltarite", "Golem", "Guemelite", "Homchai", "Human", "Unknown Race", "Undead", "Solarian"};
//    String[] checkClasses = {"Warrior", "Mage", "Marauder", "Priest", "Berserker", "Unknown Class", "Colossus", "Bard"};
//    String[] checkGuilds = {"Sap Hearts", "Kotobas", "Nehantists", "Noz'Dingard Envoys", "Zil Warriors", "Desert Nomads", "Pirates", "Runic Legion", "Stonelinkers", "Mercenaries", "Avalonians", "Winter Tribes", "Tempus"};
//    String[] checkTypes = {"Normal", "Exclusive", "Evolution", "Survival"};

    List<String> raceswanted = new ArrayList<>();
    List<String> classeswanted = new ArrayList<>();
    List<String> guildswanted = new ArrayList<>();
    List<String> typeswanted = new ArrayList<>();
    List<String> abilitieswanted = new ArrayList<>();
    List<String> specialswanted = new ArrayList<>();
    List<String> abytypeswanted = new ArrayList<>();

    ;
    public LoadScreen(String path) throws FileNotFoundException, URISyntaxException {
        initComponents();

        heroList = new ArrayList<>();
        readCSV();
        labels = new JLabel[heroList.size()];

        road = path;
        centralizarComponente();
        criando();
        poetudo();
        jLabel46.setText(heroList.size() + " heroes");
    }

    private void centralizarComponente() {
        Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dw = getSize();
        setLocation((ds.width - dw.width) / 2, (ds.height - dw.height) / 2);
    }

    private void criando() {
        int total = heroList.size();
        for (int i = 1; i <= total; i++) {
            JLabel label = new JLabel();
            label.setName("lbl" + i);
            label.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
            int x = (((i - 1) % 5) * 75 + 5) + 5 * ((i - 1) % 5);
            int y = ((i - 1) / 5 * 75 + 5) + 5 * ((i - 1) / 5);
            label.setBounds(x, y, 75, 75);
            label.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    JLabel lblchosen = (JLabel) e.getSource();
                    //        searchCode(lblchosen.getToolTipText());
                    if (!"".equals(lblchosen.getToolTipText())) {
                        for (Hero findCode : heroList) {
                            if (findCode.nome.equals(lblchosen.getToolTipText())) {
                                setChosen = findCode.set;
                            }
                        }
                    }
                    if (!"".equals(setChosen) && setChosen.length() > 50) {
                        java.awt.EventQueue.invokeLater(() -> {
                            new MainScreen(setChosen, road).setVisible(true);
                        });
                        LoadScreen.this.dispose();
                    }
                }

            });
            lbls.add(label);
            labels[i - 1] = label;
        }
    }

    protected static ImageIcon createImageIcon(String path) {
        URL imgURL = LoadScreen.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    private void readCSV() throws FileNotFoundException, URISyntaxException {

        InputStream in = getClass().getResourceAsStream("/View/AllHeroes.csv");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        Scanner bufferedScanner = new Scanner(reader);
        try {
            while (bufferedScanner.hasNextLine()) {
                String linhaCurrente = bufferedScanner.nextLine();
                try ( Scanner linhaScanner = new Scanner(linhaCurrente)) {
                    linhaScanner.useDelimiter(",");
                    heroList.add(new Hero(linhaScanner.next(), linhaScanner.next(), linhaScanner.next(), linhaScanner.next(), linhaScanner.next(), linhaScanner.next(), linhaScanner.next(), linhaScanner.next(), linhaScanner.next(), linhaScanner.next(), linhaScanner.next(), linhaScanner.next(), linhaScanner.next(), linhaScanner.next(), linhaScanner.next(), linhaScanner.next(), linhaScanner.next(), linhaScanner.next(), linhaScanner.next(), linhaScanner.next(), linhaScanner.next()));
                }
            }
        } catch (Exception anException) {
            System.out.println("Erro: " + anException);
        } finally {
            try {
                bufferedScanner.close();
            } catch (Exception anException) {
                System.out.println("Error: " + anException);
            }
        }
    }

    public void filtering() {
        List<Hero> filteredList = new ArrayList<>();
        heroList.forEach(nada -> {
            filteredList.add(nada);
        });
        poetudo();
        if (typeswanted.size() > 0) {
            for (Iterator<Hero> i = filteredList.iterator(); i.hasNext();) {
                Hero cobaia = i.next();
                boolean temUmDosTypes = false;
                for (String checkType : typeswanted) {
                    if (cobaia.tipo.equals(checkType)) {
                        temUmDosTypes = true;
                    }
                }
                if (!temUmDosTypes) {
                    i.remove();
                }
            }
        }
        if (guildswanted.size() > 0) {
            for (Iterator<Hero> i = filteredList.iterator(); i.hasNext();) {
                Hero cobaia = i.next();
                boolean temUmaDasGuild = false;
                for (String checkGuild : guildswanted) {
                    if (cobaia.guilda.equals(checkGuild)) {
                        temUmaDasGuild = true;
                    }
                }
                if (!temUmaDasGuild) {
                    i.remove();
                }
            }
        }
        if (raceswanted.size() > 0) {
            for (Iterator<Hero> i = filteredList.iterator(); i.hasNext();) {
                Hero cobaia = i.next();
                boolean temUmaDasRaces = false;
                for (String checkRace : raceswanted) {
                    if (cobaia.raca.equals(checkRace)) {
                        temUmaDasRaces = true;
                    }
                }
                if (!temUmaDasRaces) {
                    i.remove();
                }
            }
        }
        if (classeswanted.size() > 0) {
            for (Iterator<Hero> i = filteredList.iterator(); i.hasNext();) {
                Hero cobaia = i.next();
                boolean temUmaDasClasses = false;
                for (String checkClass : classeswanted) {
                    if (cobaia.classe.equals(checkClass)) {
                        temUmaDasClasses = true;
                    }
                }
                if (!temUmaDasClasses) {
                    i.remove();
                }
            }
        }
        if (abilitieswanted.size() > 0) {
            for (Iterator<Hero> i = filteredList.iterator(); i.hasNext();) {
                Hero cobaia = i.next();
                String abis[] = {"None", "None", "None", "None", "None", "None", "None", "None", "None"};
                if ((!checkB.isSelected() && !checkM.isSelected() && !checkT.isSelected()) || (checkB.isSelected() && checkM.isSelected() && checkT.isSelected())) {
                        abis[0] = cobaia.bone;
                        abis[1] = cobaia.btwo;
                        abis[2] = cobaia.bthree;
                        abis[3] = cobaia.mone;
                        abis[4] = cobaia.mtwo;
                        abis[5] = cobaia.mthree;
                        abis[6] = cobaia.tone;
                        abis[7] = cobaia.ttwo;
                        abis[8] = cobaia.tthree;
                }else{
                    if(checkB.isSelected()){
                        abis[0] = cobaia.bone;
                        abis[1] = cobaia.btwo;
                        abis[2] = cobaia.bthree;
                    }
                    if(checkM.isSelected()){
                        abis[3] = cobaia.mone;
                        abis[4] = cobaia.mtwo;
                        abis[5] = cobaia.mthree;
                    }
                    if(checkT.isSelected()){
                        abis[6] = cobaia.tone;
                        abis[7] = cobaia.ttwo;
                        abis[8] = cobaia.tthree;
                    }
                }
//                    cobaia.bone,cobaia.btwo,cobaia.bthree,cobaia.mone,cobaia.mtwo,cobaia.mthree,cobaia.tone,cobaia.ttwo,cobaia.tthree};
                boolean temUmaDasAbilities = false;
                for (String checkAbility : abilitieswanted) {
                    for (int k = 0; k < 9; k++) {
                        if (abis[k].equals(checkAbility)) {
                            temUmaDasAbilities = true;
                        }
                    }

                }
                if (jRadioButton2.isSelected()) {
                    if (temUmaDasAbilities) {
                        temUmaDasAbilities = false;
                    } else {
                        temUmaDasAbilities = true;
                    }
                }
                if (!temUmaDasAbilities) {
                    i.remove();
                }
            }
        }
        if (specialswanted.size() > 0) {
            for (Iterator<Hero> i = filteredList.iterator(); i.hasNext();) {
                Hero cobaia = i.next();
                boolean temUmaDasSpecials = false;
                for (String checkSpecial : specialswanted) {
                    if (cobaia.special1.equals(checkSpecial)) {
                        temUmaDasSpecials = true;
                    }
                    if (cobaia.special2.equals(checkSpecial)) {
                        temUmaDasSpecials = true;
                    }
                    if (cobaia.special3.equals(checkSpecial)) {
                        temUmaDasSpecials = true;
                    }
                }
                if (!temUmaDasSpecials) {
                    i.remove();
                }
            }
        } 
        if (abytypeswanted.size() > 0) {
            for (Iterator<Hero> i = filteredList.iterator(); i.hasNext();) {
                Hero cobaia = i.next();
                boolean temUmaDasTypes = false;
                for (String checkType : abytypeswanted) {
                    if (cobaia.abyType1.equals(checkType)) {
                        temUmaDasTypes = true;
                    }
                    if (cobaia.abyType2.equals(checkType)) {
                        temUmaDasTypes = true;
                    }
                    if (cobaia.abyType3.equals(checkType)) {
                        temUmaDasTypes = true;
                    }
                }
                if (!temUmaDasTypes) {
                    i.remove();
                }
            }
        } 
        int i = 0;
        apagao();
        for (Hero filteringHero : filteredList) {
            labels[i].setVisible(true);
            labels[i].setIcon(createImageIcon("/Shibis/" + filteringHero.nome + ".png"));
            labels[i].setToolTipText(filteringHero.nome);
            labels[i].setBorder(null);
            i++;
        }
        jLabel46.setText(filteredList.size() + " hero(es)");
    }

    private void apagao() {
        for (JLabel label : labels) {
            label.setVisible(false);
        }
    }

    private void poetudo() {
        int i = 0;
        for (Hero card : heroList) {
            labels[i].setVisible(true);
            labels[i].setIcon(createImageIcon("/Shibis/" + card.nome + ".png"));
            labels[i].setToolTipText(card.nome);
            labels[i].setBorder(null);
            i++;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        lbls = new javax.swing.JPanel();
        checkBasic = new javax.swing.JCheckBox();
        checkPremium = new javax.swing.JCheckBox();
        checkEX = new javax.swing.JCheckBox();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        checkEV = new javax.swing.JCheckBox();
        jLabel42 = new javax.swing.JLabel();
        checkSV = new javax.swing.JCheckBox();
        jLabel35 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        btnReset = new javax.swing.JButton();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        checkSH = new javax.swing.JCheckBox();
        checkKOT = new javax.swing.JCheckBox();
        checkNEH = new javax.swing.JCheckBox();
        checkNOZ = new javax.swing.JCheckBox();
        checkZIL = new javax.swing.JCheckBox();
        checkNOM = new javax.swing.JCheckBox();
        checkPIR = new javax.swing.JCheckBox();
        checkRL = new javax.swing.JCheckBox();
        checkSL = new javax.swing.JCheckBox();
        checkMER = new javax.swing.JCheckBox();
        checkAVA = new javax.swing.JCheckBox();
        checkWT = new javax.swing.JCheckBox();
        checkTEM = new javax.swing.JCheckBox();
        jLabel37 = new javax.swing.JLabel();
        checkBard = new javax.swing.JCheckBox();
        checkBerserker = new javax.swing.JCheckBox();
        checkColossus = new javax.swing.JCheckBox();
        checkMage = new javax.swing.JCheckBox();
        checkMarauder = new javax.swing.JCheckBox();
        checkPriest = new javax.swing.JCheckBox();
        checkUnkClass = new javax.swing.JCheckBox();
        checkWarrior = new javax.swing.JCheckBox();
        jLabel27 = new javax.swing.JLabel();
        checkBeast = new javax.swing.JCheckBox();
        checkDais = new javax.swing.JCheckBox();
        checkDemon = new javax.swing.JCheckBox();
        checkDragon = new javax.swing.JCheckBox();
        checkIceElf = new javax.swing.JCheckBox();
        checkElfine = new javax.swing.JCheckBox();
        checkEltarite = new javax.swing.JCheckBox();
        checkGolem = new javax.swing.JCheckBox();
        checkGuem = new javax.swing.JCheckBox();
        checkHomchai = new javax.swing.JCheckBox();
        checkHuman = new javax.swing.JCheckBox();
        checkSolarian = new javax.swing.JCheckBox();
        checkUndead = new javax.swing.JCheckBox();
        checkUnkRace = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        checkBackstab = new javax.swing.JCheckBox();
        checkBerserk = new javax.swing.JCheckBox();
        checkBlessing = new javax.swing.JCheckBox();
        checkBlizzard = new javax.swing.JCheckBox();
        checkBulwark = new javax.swing.JCheckBox();
        checkCritical = new javax.swing.JCheckBox();
        checkDeathstare = new javax.swing.JCheckBox();
        checkDejavu = new javax.swing.JCheckBox();
        checkDC = new javax.swing.JCheckBox();
        checkBDMG = new javax.swing.JCheckBox();
        checkMDMG = new javax.swing.JCheckBox();
        checkDodge = new javax.swing.JCheckBox();
        checkEclipse = new javax.swing.JCheckBox();
        checkFireball = new javax.swing.JCheckBox();
        checkFrostbite = new javax.swing.JCheckBox();
        checkHeal = new javax.swing.JCheckBox();
        checkHit = new javax.swing.JCheckBox();
        checkIce = new javax.swing.JCheckBox();
        checkInspire = new javax.swing.JCheckBox();
        checkLD = new javax.swing.JCheckBox();
        checkLightning = new javax.swing.JCheckBox();
        checkMimic = new javax.swing.JCheckBox();
        checkPA = new javax.swing.JCheckBox();
        checkPortal = new javax.swing.JCheckBox();
        checkPowder = new javax.swing.JCheckBox();
        checkPurify = new javax.swing.JCheckBox();
        checkResilience = new javax.swing.JCheckBox();
        checkRiposte = new javax.swing.JCheckBox();
        checkRune = new javax.swing.JCheckBox();
        checkScarab = new javax.swing.JCheckBox();
        checkShield = new javax.swing.JCheckBox();
        checkShieldBash = new javax.swing.JCheckBox();
        checkShock = new javax.swing.JCheckBox();
        checkSmite = new javax.swing.JCheckBox();
        checkSB = new javax.swing.JCheckBox();
        checkStench = new javax.swing.JCheckBox();
        checkSTRPlus = new javax.swing.JCheckBox();
        checkSTRMinus = new javax.swing.JCheckBox();
        checkSTRx = new javax.swing.JCheckBox();
        checkSTRDivide = new javax.swing.JCheckBox();
        checkODC = new javax.swing.JCheckBox();
        checkSTREquals = new javax.swing.JCheckBox();
        checkSTRDrain = new javax.swing.JCheckBox();
        checkSymbiosis = new javax.swing.JCheckBox();
        checkTerror = new javax.swing.JCheckBox();
        checkThorn = new javax.swing.JCheckBox();
        checkThunder = new javax.swing.JCheckBox();
        jLabel48 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        checkB = new javax.swing.JCheckBox();
        checkM = new javax.swing.JCheckBox();
        checkT = new javax.swing.JCheckBox();
        jLabel49 = new javax.swing.JLabel();
        checkDicex = new javax.swing.JCheckBox();
        checkXDice = new javax.swing.JCheckBox();
        checkXAlly = new javax.swing.JCheckBox();
        checkBonMal = new javax.swing.JCheckBox();
        checkAtk = new javax.swing.JCheckBox();
        checkDfd = new javax.swing.JCheckBox();
        checkVS = new javax.swing.JCheckBox();
        jLabel50 = new javax.swing.JLabel();
        checkBrave = new javax.swing.JCheckBox();
        checkIcy = new javax.swing.JCheckBox();
        checkNoble = new javax.swing.JCheckBox();
        checkRunic = new javax.swing.JCheckBox();
        checkSupport = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setHorizontalScrollBar(null);

        lbls.setPreferredSize(new java.awt.Dimension(611, 11000));

        javax.swing.GroupLayout lblsLayout = new javax.swing.GroupLayout(lbls);
        lbls.setLayout(lblsLayout);
        lblsLayout.setHorizontalGroup(
            lblsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 611, Short.MAX_VALUE)
        );
        lblsLayout.setVerticalGroup(
            lblsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 11000, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(lbls);

        checkBasic.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkSVItemStateChanged(evt);
            }
        });

        checkPremium.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkSVItemStateChanged(evt);
            }
        });

        checkEX.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkSVItemStateChanged(evt);
            }
        });

        jLabel38.setBackground(new java.awt.Color(255, 255, 255));
        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Filters/Ex.png"))); // NOI18N

        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Filters/Sv.png"))); // NOI18N

        jLabel40.setText("Basic");

        jLabel41.setText("Premium");

        checkEV.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkSVItemStateChanged(evt);
            }
        });

        jLabel42.setBackground(new java.awt.Color(255, 255, 255));
        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Filters/ev.png"))); // NOI18N

        checkSV.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkSVItemStateChanged(evt);
            }
        });

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Filters/warrior.png"))); // NOI18N

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Filters/IceElf.png"))); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Filters/Zils.png"))); // NOI18N

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Filters/Tempus.png"))); // NOI18N

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Filters/berserker.png"))); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Filters/Stonelinkers.png"))); // NOI18N

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Filters/Guemelite.png"))); // NOI18N

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Filters/Undead.png"))); // NOI18N

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Filters/Beast.png"))); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Filters/sap.png"))); // NOI18N

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Filters/mage.png"))); // NOI18N

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Filters/Mercenary.png"))); // NOI18N

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Filters/unknownclass.png"))); // NOI18N

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Filters/Dais.png"))); // NOI18N

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Filters/kot.png"))); // NOI18N

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Filters/Homchai.png"))); // NOI18N

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Filters/Solarian.png"))); // NOI18N

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Filters/Nomads.png"))); // NOI18N

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Filters/Elfine.png"))); // NOI18N

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Filters/marauder.png"))); // NOI18N

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Filters/Eltarite.png"))); // NOI18N

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Filters/Human.png"))); // NOI18N

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Filters/Nehant.png"))); // NOI18N

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Filters/colossus.png"))); // NOI18N

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Filters/Demon.png"))); // NOI18N

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Filters/Pirates.png"))); // NOI18N

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Filters/Avalonian.png"))); // NOI18N

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Filters/bard.png"))); // NOI18N

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Filters/UnknownRace.png"))); // NOI18N

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Filters/noz.png"))); // NOI18N

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Filters/Runic.png"))); // NOI18N

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Filters/Winter.png"))); // NOI18N

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Filters/priest.png"))); // NOI18N

        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Filters/Dragon.png"))); // NOI18N

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Filters/Golem.png"))); // NOI18N

        btnReset.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnReset.setText("Reset Filters");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("Filters");

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel45.setText("Types");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel28.setText("Guilds");

        checkSH.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkSHItemStateChanged(evt);
            }
        });

        checkKOT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkSHItemStateChanged(evt);
            }
        });

        checkNEH.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkSHItemStateChanged(evt);
            }
        });

        checkNOZ.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkSHItemStateChanged(evt);
            }
        });

        checkZIL.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkSHItemStateChanged(evt);
            }
        });

        checkNOM.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkSHItemStateChanged(evt);
            }
        });

        checkPIR.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkSHItemStateChanged(evt);
            }
        });

        checkRL.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkSHItemStateChanged(evt);
            }
        });

        checkSL.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkSHItemStateChanged(evt);
            }
        });

        checkMER.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkSHItemStateChanged(evt);
            }
        });

        checkAVA.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkSHItemStateChanged(evt);
            }
        });

        checkWT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkSHItemStateChanged(evt);
            }
        });

        checkTEM.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkSHItemStateChanged(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel37.setText("Classes");

        checkBard.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkUnkClassItemStateChanged(evt);
            }
        });

        checkBerserker.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkUnkClassItemStateChanged(evt);
            }
        });

        checkColossus.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkUnkClassItemStateChanged(evt);
            }
        });

        checkMage.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkUnkClassItemStateChanged(evt);
            }
        });

        checkMarauder.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkUnkClassItemStateChanged(evt);
            }
        });

        checkPriest.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkUnkClassItemStateChanged(evt);
            }
        });

        checkUnkClass.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkUnkClassItemStateChanged(evt);
            }
        });

        checkWarrior.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkUnkClassItemStateChanged(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel27.setText("Races");

        checkBeast.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkBeastItemStateChanged(evt);
            }
        });

        checkDais.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkBeastItemStateChanged(evt);
            }
        });

        checkDemon.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkBeastItemStateChanged(evt);
            }
        });

        checkDragon.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkBeastItemStateChanged(evt);
            }
        });

        checkIceElf.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkBeastItemStateChanged(evt);
            }
        });

        checkElfine.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkBeastItemStateChanged(evt);
            }
        });

        checkEltarite.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkBeastItemStateChanged(evt);
            }
        });

        checkGolem.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkBeastItemStateChanged(evt);
            }
        });

        checkGuem.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkBeastItemStateChanged(evt);
            }
        });

        checkHomchai.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkBeastItemStateChanged(evt);
            }
        });

        checkHuman.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkBeastItemStateChanged(evt);
            }
        });

        checkSolarian.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkBeastItemStateChanged(evt);
            }
        });

        checkUndead.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkBeastItemStateChanged(evt);
            }
        });

        checkUnkRace.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkBeastItemStateChanged(evt);
            }
        });

        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jLabel46.setBackground(new java.awt.Color(255, 102, 51));
        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setText("Char numbers");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel46, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setText("Abilities");

        checkBackstab.setText("Backstab");
        checkBackstab.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        checkBerserk.setText("Berserk");
        checkBerserk.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        checkBlessing.setText("Blessing");
        checkBlessing.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        checkBlizzard.setText("Blizzard");
        checkBlizzard.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        checkBulwark.setText("Bulwark");
        checkBulwark.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        checkCritical.setText("Critical");
        checkCritical.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        checkDeathstare.setText("Death Stare");
        checkDeathstare.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        checkDejavu.setText("Deja Vu");
        checkDejavu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        checkDC.setText("Dice Change");
        checkDC.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        checkBDMG.setText("+DMG");
        checkBDMG.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        checkMDMG.setText("-DMG");
        checkMDMG.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        checkDodge.setText("Dodge");
        checkDodge.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        checkEclipse.setText("Eclipse");
        checkEclipse.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        checkFireball.setText("Fireball");
        checkFireball.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        checkFrostbite.setText("Frostbite");
        checkFrostbite.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        checkHeal.setText("Heal");
        checkHeal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        checkHit.setText("Hit");
        checkHit.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        checkIce.setText("Ice");
        checkIce.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        checkInspire.setText("Inspire");
        checkInspire.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        checkLD.setText("Life Drain");
        checkLD.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        checkLightning.setText("Lightning");
        checkLightning.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        checkMimic.setText("Mimic");
        checkMimic.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        checkPA.setText("Physical Attack");
        checkPA.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        checkPortal.setText("Portal");
        checkPortal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        checkPowder.setText("Powder");
        checkPowder.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        checkPurify.setText("Purify");
        checkPurify.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        checkResilience.setText("Resilience");
        checkResilience.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        checkRiposte.setText("Riposte");
        checkRiposte.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        checkRune.setText("Rune");
        checkRune.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        checkScarab.setText("Scarab");
        checkScarab.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        checkShield.setText("Shield");
        checkShield.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        checkShieldBash.setText("Shield Bash");
        checkShieldBash.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        checkShock.setText("Shock");
        checkShock.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        checkSmite.setText("Smite");
        checkSmite.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        checkSB.setText("Spellbreaker");
        checkSB.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        checkStench.setText("Stench");
        checkStench.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        checkSTRPlus.setText("+STR");
        checkSTRPlus.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        checkSTRMinus.setText("STR -");
        checkSTRMinus.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        checkSTRx.setText("STR x");
        checkSTRx.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        checkSTRDivide.setText("STR /");
        checkSTRDivide.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        checkODC.setText("opp Dice Change");
        checkODC.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        checkSTREquals.setText("STR =");
        checkSTREquals.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        checkSTRDrain.setText("Strength Drain");
        checkSTRDrain.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        checkSymbiosis.setText("Symbiosis");
        checkSymbiosis.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        checkTerror.setText("Terror");
        checkTerror.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        checkThorn.setText("Thorn");
        checkThorn.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        checkThunder.setText("Thunderstruck");
        checkThunder.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        jLabel48.setBackground(new java.awt.Color(0, 0, 0));

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("I want...");
        jRadioButton1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton2ItemStateChanged(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRadioButton2.setText("I do not want...");
        jRadioButton2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton2ItemStateChanged(evt);
            }
        });

        checkB.setText("in 1st");
        checkB.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkBItemStateChanged(evt);
            }
        });

        checkM.setText("in 2nd");
        checkM.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkBItemStateChanged(evt);
            }
        });

        checkT.setText("in 3rd");
        checkT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkBItemStateChanged(evt);
            }
        });

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel49.setText("Features");

        checkDicex.setText("DiceX");
        checkDicex.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkXDiceItemStateChanged(evt);
            }
        });

        checkXDice.setText("per Dice");
        checkXDice.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkXDiceItemStateChanged(evt);
            }
        });

        checkXAlly.setText("per Ally");
        checkXAlly.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkXDiceItemStateChanged(evt);
            }
        });

        checkBonMal.setText("per Bonus/Malus");
        checkBonMal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkXDiceItemStateChanged(evt);
            }
        });

        checkAtk.setText("Attacker");
        checkAtk.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkXDiceItemStateChanged(evt);
            }
        });

        checkDfd.setText("Defender");
        checkDfd.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkXDiceItemStateChanged(evt);
            }
        });

        checkVS.setText("VS");
        checkVS.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkXDiceItemStateChanged(evt);
            }
        });

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel50.setText("Ability Types");

        checkBrave.setText("Brave");
        checkBrave.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkRunicItemStateChanged(evt);
            }
        });

        checkIcy.setText("Icy");
        checkIcy.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkRunicItemStateChanged(evt);
            }
        });

        checkNoble.setText("Noble");
        checkNoble.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkRunicItemStateChanged(evt);
            }
        });

        checkRunic.setText("Runic");
        checkRunic.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkRunicItemStateChanged(evt);
            }
        });

        checkSupport.setText("Support");
        checkSupport.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkLightningItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(21, 21, 21)
                                    .addComponent(checkRL)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel9)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(checkSL)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(checkMER)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel10)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(checkAVA)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel11)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(checkWT)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel12)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(checkTEM)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel13))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(checkBasic)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel40)
                                    .addGap(12, 12, 12)
                                    .addComponent(checkPremium)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel41)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(checkEX)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel38)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(checkEV)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel42)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(checkSV)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel39))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(checkBard)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel34)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(checkBerserker)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel36)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(checkColossus)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel33)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(checkMage)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel29))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(checkSH)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(checkKOT)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(checkNEH)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(checkNOZ)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(checkZIL)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(checkNOM)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(checkPIR)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel8))
                                .addComponent(jLabel28)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(checkMarauder)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel30)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(checkPriest)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel31)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(checkUnkClass)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel32)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(checkWarrior)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel35))
                                .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(checkBeast)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(checkDais)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(checkDemon)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(checkDragon)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel43)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(checkIceElf)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(checkElfine)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(checkEltarite)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel17))
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(checkGolem)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel18)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(checkGuem)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel19)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(checkHomchai)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel26)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(checkHuman)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel20)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(checkSolarian)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel25))
                                    .addComponent(jButton2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(checkUndead)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(checkUnkRace)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel21))
                            .addComponent(jLabel49)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(checkDicex)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(checkXDice)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(checkXAlly)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(checkVS))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(checkBonMal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(checkAtk)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(checkDfd))
                            .addComponent(jLabel50))
                        .addGap(8, 8, 8)
                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(checkBrave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkIcy)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkNoble)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkRunic)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkBackstab)
                            .addComponent(checkBerserk)
                            .addComponent(checkBlessing)
                            .addComponent(checkBlizzard)
                            .addComponent(checkBulwark)
                            .addComponent(checkCritical)
                            .addComponent(checkDeathstare)
                            .addComponent(checkDejavu)
                            .addComponent(checkDC)
                            .addComponent(checkBDMG)
                            .addComponent(checkMDMG)
                            .addComponent(checkDodge)
                            .addComponent(checkEclipse)
                            .addComponent(checkFireball)
                            .addComponent(checkFrostbite)
                            .addComponent(checkHeal)
                            .addComponent(checkHit)
                            .addComponent(checkIce)
                            .addComponent(checkInspire)
                            .addComponent(checkLD)
                            .addComponent(checkLightning)
                            .addComponent(checkMimic)
                            .addComponent(checkPA)
                            .addComponent(checkODC))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkPortal)
                            .addComponent(checkPowder)
                            .addComponent(checkPurify)
                            .addComponent(checkResilience)
                            .addComponent(checkRiposte)
                            .addComponent(checkRune)
                            .addComponent(checkScarab)
                            .addComponent(checkShield)
                            .addComponent(checkShieldBash)
                            .addComponent(checkShock)
                            .addComponent(checkSmite)
                            .addComponent(checkSB)
                            .addComponent(checkStench)
                            .addComponent(checkSTRPlus)
                            .addComponent(checkSTRMinus)
                            .addComponent(checkSTRx)
                            .addComponent(checkSTRDivide)
                            .addComponent(checkSTREquals)
                            .addComponent(checkSTRDrain)
                            .addComponent(checkSupport)
                            .addComponent(checkSymbiosis)
                            .addComponent(checkTerror)
                            .addComponent(checkThorn)
                            .addComponent(checkThunder))
                        .addContainerGap(57, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRadioButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton2))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(checkB)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(checkM)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(checkT)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(468, 468, 468)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnReset)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel44)
                                .addComponent(btnReset)
                                .addComponent(jLabel47))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(170, 170, 170))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel45)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jRadioButton1)
                                        .addComponent(jRadioButton2)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                            .addComponent(checkBasic)
                                            .addComponent(checkPremium)
                                            .addComponent(jLabel40)
                                            .addComponent(jLabel41)
                                            .addComponent(checkEX)
                                            .addComponent(checkEV)
                                            .addComponent(jLabel42)
                                            .addComponent(checkSV)
                                            .addComponent(jLabel39)
                                            .addComponent(jLabel38))
                                        .addGap(20, 20, 20)
                                        .addComponent(jLabel28)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                            .addComponent(checkSH)
                                            .addComponent(checkKOT)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel4)
                                            .addComponent(checkNEH)
                                            .addComponent(jLabel5)
                                            .addComponent(checkNOZ)
                                            .addComponent(jLabel6)
                                            .addComponent(checkZIL)
                                            .addComponent(jLabel2)
                                            .addComponent(checkNOM)
                                            .addComponent(jLabel7)
                                            .addComponent(checkPIR)
                                            .addComponent(jLabel8))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                            .addComponent(jLabel9)
                                            .addComponent(checkRL)
                                            .addComponent(checkSL)
                                            .addComponent(jLabel3)
                                            .addComponent(checkMER)
                                            .addComponent(jLabel10)
                                            .addComponent(checkAVA)
                                            .addComponent(jLabel11)
                                            .addComponent(checkWT)
                                            .addComponent(jLabel12)
                                            .addComponent(checkTEM)
                                            .addComponent(jLabel13))
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel37)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                            .addComponent(jLabel34)
                                            .addComponent(checkBard)
                                            .addComponent(checkBerserker)
                                            .addComponent(jLabel36)
                                            .addComponent(checkColossus)
                                            .addComponent(jLabel33)
                                            .addComponent(checkMage)
                                            .addComponent(jLabel29))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                            .addComponent(checkMarauder)
                                            .addComponent(jLabel30)
                                            .addComponent(checkPriest)
                                            .addComponent(jLabel31)
                                            .addComponent(checkUnkClass)
                                            .addComponent(checkWarrior)
                                            .addComponent(jLabel32)
                                            .addComponent(jLabel35))
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel27)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                            .addComponent(jLabel22)
                                            .addComponent(checkBeast)
                                            .addComponent(checkDais)
                                            .addComponent(jLabel14)
                                            .addComponent(checkDemon)
                                            .addComponent(jLabel15)
                                            .addComponent(checkDragon)
                                            .addComponent(jLabel43)
                                            .addComponent(checkIceElf)
                                            .addComponent(jLabel16)
                                            .addComponent(checkElfine)
                                            .addComponent(jLabel24)
                                            .addComponent(checkEltarite)
                                            .addComponent(jLabel17))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                            .addComponent(checkGolem)
                                            .addComponent(jLabel18)
                                            .addComponent(checkGuem)
                                            .addComponent(jLabel19)
                                            .addComponent(checkHomchai)
                                            .addComponent(jLabel26)
                                            .addComponent(checkHuman)
                                            .addComponent(jLabel20)
                                            .addComponent(checkSolarian)
                                            .addComponent(jLabel25)
                                            .addComponent(checkUndead)
                                            .addComponent(jLabel23)
                                            .addComponent(checkUnkRace)
                                            .addComponent(jLabel21))
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel49)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(checkDicex)
                                            .addComponent(checkXDice)
                                            .addComponent(checkXAlly)
                                            .addComponent(checkVS))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(checkBonMal)
                                            .addComponent(checkAtk)
                                            .addComponent(checkDfd)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(checkB)
                                            .addComponent(checkM)
                                            .addComponent(checkT))
                                        .addGap(15, 15, 15)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(checkPortal)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(checkPowder)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(checkPurify)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(checkResilience)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(checkRiposte)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(checkRune)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(checkScarab)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(checkShield)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(checkShieldBash)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(checkShock)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(checkSmite)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(checkSB)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(checkStench)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(checkSTRPlus)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(checkSTRMinus)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(checkSTRx))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(checkBackstab)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(checkBerserk)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(checkBlessing)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(checkBlizzard)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(checkBulwark)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(checkCritical)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(checkDeathstare)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(checkDejavu)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(checkDC)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(checkODC)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(checkBDMG)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(checkMDMG)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(checkDodge)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(checkEclipse)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(checkFireball)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(checkFrostbite)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(checkHit)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(checkHeal)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(checkIce))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel50)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                            .addComponent(checkBrave)
                                                            .addComponent(checkIcy)
                                                            .addComponent(checkNoble)
                                                            .addComponent(checkRunic))))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(checkInspire)
                                                    .addComponent(checkSupport))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(checkLD)
                                                    .addComponent(checkSymbiosis))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(checkLightning)
                                                    .addComponent(checkTerror))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(checkMimic)
                                                    .addComponent(checkThorn))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(checkPA)
                                                    .addComponent(checkThunder)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(checkSTRDivide)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(checkSTREquals)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(checkSTRDrain)))))
                                .addGap(0, 18, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton2))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void checkSVItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkSVItemStateChanged
        if (!typeswanted.isEmpty()) {
            typeswanted.clear();
        }
        if (checkBasic.isSelected()) {
            typeswanted.add("Basic");
        }
        if (checkPremium.isSelected()) {
            typeswanted.add("Premium");
        }
        if (checkEX.isSelected()) {
            typeswanted.add("Exclusive");
        }
        if (checkEV.isSelected()) {
            typeswanted.add("Evolution");
        }
        if (checkSV.isSelected()) {
            typeswanted.add("Survival");
        }
        filtering();
    }//GEN-LAST:event_checkSVItemStateChanged

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed

    }//GEN-LAST:event_btnResetActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        java.awt.EventQueue.invokeLater(() -> {
            new MainScreen("", road).setVisible(true);
        });
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void checkSHItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkSHItemStateChanged
        if (!guildswanted.isEmpty()) {
            guildswanted.clear();
        }
        if (checkSH.isSelected()) {
            guildswanted.add("Sap Hearts");
        }
        if (checkKOT.isSelected()) {
            guildswanted.add("Kotoba");
        }
        if (checkNEH.isSelected()) {
            guildswanted.add("Nehantists");
        }
        if (checkNOZ.isSelected()) {
            guildswanted.add("Noz'dingard Envoys");
        }
        if (checkZIL.isSelected()) {
            guildswanted.add("Zil Warriors");
        }
        if (checkNOM.isSelected()) {
            guildswanted.add("Desert Nomads");
        }
        if (checkPIR.isSelected()) {
            guildswanted.add("Pirates");
        }
        if (checkRL.isSelected()) {
            guildswanted.add("Runic Legion");
        }
        if (checkSL.isSelected()) {
            guildswanted.add("Stonelinkers");
        }
        if (checkMER.isSelected()) {
            guildswanted.add("Mercenaries");
        }
        if (checkAVA.isSelected()) {
            guildswanted.add("Avalonians");
        }
        if (checkWT.isSelected()) {
            guildswanted.add("Winter Trybes");
        }
        if (checkTEM.isSelected()) {
            guildswanted.add("Tempus");
        }
        filtering();
    }//GEN-LAST:event_checkSHItemStateChanged

    private void checkUnkClassItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkUnkClassItemStateChanged
        if (!classeswanted.isEmpty()) {
            classeswanted.clear();
        }
        if (checkBard.isSelected()) {
            classeswanted.add("Bard");
        }
        if (checkBerserker.isSelected()) {
            classeswanted.add("Berserker");
        }
        if (checkColossus.isSelected()) {
            classeswanted.add("Colossus");
        }
        if (checkMage.isSelected()) {
            classeswanted.add("Mage");
        }
        if (checkMarauder.isSelected()) {
            classeswanted.add("Marauder");
        }
        if (checkPriest.isSelected()) {
            classeswanted.add("Priest");
        }
        if (checkUnkClass.isSelected()) {
            classeswanted.add("UnkClass");
        }
        if (checkWarrior.isSelected()) {
            classeswanted.add("Warrior");
        }
        filtering();
    }//GEN-LAST:event_checkUnkClassItemStateChanged

    private void checkBeastItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkBeastItemStateChanged
        if (!raceswanted.isEmpty()) {
            raceswanted.clear();
        }
        if (checkBeast.isSelected()) {
            raceswanted.add("Beast");
        }
        if (checkDais.isSelected()) {
            raceswanted.add("Dais");
        }
        if (checkDemon.isSelected()) {
            raceswanted.add("Demon");
        }
        if (checkDragon.isSelected()) {
            raceswanted.add("Dragon");
        }
        if (checkIceElf.isSelected()) {
            raceswanted.add("Ice Elf");
        }
        if (checkElfine.isSelected()) {
            raceswanted.add("Elfine");
        }
        if (checkEltarite.isSelected()) {
            raceswanted.add("Eltarite");
        }
        if (checkGolem.isSelected()) {
            raceswanted.add("Golem");
        }
        if (checkGuem.isSelected()) {
            raceswanted.add("Guemelite");
        }
        if (checkHomchai.isSelected()) {
            raceswanted.add("Homchai");
        }
        if (checkHuman.isSelected()) {
            raceswanted.add("Human");
        }
        if (checkSolarian.isSelected()) {
            raceswanted.add("Solarian");
        }
        if (checkUndead.isSelected()) {
            raceswanted.add("Undead");
        }
        if (checkUnkRace.isSelected()) {
            raceswanted.add("UnkRace");
        }
        filtering();
    }//GEN-LAST:event_checkBeastItemStateChanged

    private void checkLightningItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkLightningItemStateChanged
        if (!abilitieswanted.isEmpty()) {
            abilitieswanted.clear();
        }
        if (checkBackstab.isSelected()) {
            abilitieswanted.add("Backstab");
        }
        if (checkBerserk.isSelected()) {
            abilitieswanted.add("Berserk");
        }
        if (checkBlessing.isSelected()) {
            abilitieswanted.add("Blessing");
        }
        if (checkBlizzard.isSelected()) {
            abilitieswanted.add("Blizzard");
        }
        if (checkBulwark.isSelected()) {
            abilitieswanted.add("Bulwark");
        }
        if (checkCritical.isSelected()) {
            abilitieswanted.add("Critical");
        }
        if (checkDeathstare.isSelected()) {
            abilitieswanted.add("Death Stare");
        }
        if (checkDejavu.isSelected()) {
            abilitieswanted.add("Deja Vu");
        }
        if (checkDC.isSelected()) {
            abilitieswanted.add("DC");
        }
        if (checkODC.isSelected()) {
            abilitieswanted.add("opp DC");
        }
        if (checkBDMG.isSelected()) {
            abilitieswanted.add("+DMG");
        }
        if (checkMDMG.isSelected()) {
            abilitieswanted.add("-DMG");
        }
        if (checkDodge.isSelected()) {
            abilitieswanted.add("Dodge");
        }
        if (checkEclipse.isSelected()) {
            abilitieswanted.add("Eclipse");
        }
        if (checkFireball.isSelected()) {
            abilitieswanted.add("Fireball");
        }
        if (checkFrostbite.isSelected()) {
            abilitieswanted.add("Frostbite");
        }
        if (checkHit.isSelected()) {
            abilitieswanted.add("Hit");
        }
        if (checkHeal.isSelected()) {
            abilitieswanted.add("Heal");
        }
        if (checkIce.isSelected()) {
            abilitieswanted.add("Ice");
        }
        if (checkInspire.isSelected()) {
            abilitieswanted.add("Inspire");
        }
        if (checkLD.isSelected()) {
            abilitieswanted.add("Life Drain");
        }
        if (checkLightning.isSelected()) {
            abilitieswanted.add("Lightning");
        }
        if (checkMimic.isSelected()) {
            abilitieswanted.add("Mimic");
        }
        if (checkPA.isSelected()) {
            abilitieswanted.add("Physical Attack");
        }
        if (checkPortal.isSelected()) {
            abilitieswanted.add("Portal");
        }
        if (checkPowder.isSelected()) {
            abilitieswanted.add("Powder");
        }
        if (checkPurify.isSelected()) {
            abilitieswanted.add("Purify");
        }
        if (checkResilience.isSelected()) {
            abilitieswanted.add("Resilience");
        }
        if (checkRiposte.isSelected()) {
            abilitieswanted.add("Riposte");
        }
        if (checkRune.isSelected()) {
            abilitieswanted.add("Rune");
        }
        if (checkScarab.isSelected()) {
            abilitieswanted.add("Scarab");
        }
        if (checkShield.isSelected()) {
            abilitieswanted.add("Shield");
        }
        if (checkShieldBash.isSelected()) {
            abilitieswanted.add("Shield Bash");
        }
        if (checkShock.isSelected()) {
            abilitieswanted.add("Shock");
        }
        if (checkSmite.isSelected()) {
            abilitieswanted.add("Smite");
        }
        if (checkSB.isSelected()) {
            abilitieswanted.add("Spellbreaker");
        }
        if (checkStench.isSelected()) {
            abilitieswanted.add("Stench");
        }
        if (checkSTRPlus.isSelected()) {
            abilitieswanted.add("+STR");
        }
        if (checkSTRMinus.isSelected()) {
            abilitieswanted.add("STR -");
        }
        if (checkSTRx.isSelected()) {
            abilitieswanted.add("STR x");
        }
        if (checkSTRDivide.isSelected()) {
            abilitieswanted.add("STR /");
        }
        if (checkSTREquals.isSelected()) {
            abilitieswanted.add("STR =");
        }
        if (checkSTRDrain.isSelected()) {
            abilitieswanted.add("Strength Drain");
        }
        if (checkSymbiosis.isSelected()) {
            abilitieswanted.add("Symbiosis");
        }
        if (checkSupport.isSelected()) {
            abilitieswanted.add("Support");
        }
        if (checkTerror.isSelected()) {
            abilitieswanted.add("Terror");
        }
        if (checkThorn.isSelected()) {
            abilitieswanted.add("Thorn");
        }
        if (checkThunder.isSelected()) {
            abilitieswanted.add("Thunderstruck");
        }
        filtering();
    }//GEN-LAST:event_checkLightningItemStateChanged

    private void jRadioButton2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton2ItemStateChanged
        filtering();
    }//GEN-LAST:event_jRadioButton2ItemStateChanged

    private void checkBItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkBItemStateChanged
        filtering();
    }//GEN-LAST:event_checkBItemStateChanged

    private void checkXDiceItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkXDiceItemStateChanged
        if (!specialswanted.isEmpty()) {
            specialswanted.clear();
        }
        if (checkDicex.isSelected()) {
            specialswanted.add("DiceX");
        }
        if (checkXDice.isSelected()) {
            specialswanted.add("per Dice");
        }
        if (checkXAlly.isSelected()) {
            specialswanted.add("per Ally");
        }
        if (checkBonMal.isSelected()) {
            specialswanted.add("per Bonus/Malus");
        }
        if (checkAtk.isSelected()) {
            specialswanted.add("Attacker");
        }
        if (checkDfd.isSelected()) {
            specialswanted.add("Defender");
        }
        if (checkVS.isSelected()) {
            specialswanted.add("VS");
        }
        filtering();
    }//GEN-LAST:event_checkXDiceItemStateChanged

    private void checkRunicItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkRunicItemStateChanged
        if (!abytypeswanted.isEmpty()) {
            abytypeswanted.clear();
        }
        if (checkBrave.isSelected()) {
            abytypeswanted.add("Brave");
        }
        if (checkIcy.isSelected()) {
            abytypeswanted.add("Icy");
        }
        if (checkNoble.isSelected()) {
            abytypeswanted.add("Noble");
        }
        if (checkRunic.isSelected()) {
            abytypeswanted.add("Runic");
        }
        filtering();
    }//GEN-LAST:event_checkRunicItemStateChanged

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoadScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new LoadScreen("").setVisible(true);
            } catch (FileNotFoundException | URISyntaxException ex) {
                Logger.getLogger(LoadScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReset;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox checkAVA;
    private javax.swing.JCheckBox checkAtk;
    private javax.swing.JCheckBox checkB;
    private javax.swing.JCheckBox checkBDMG;
    private javax.swing.JCheckBox checkBackstab;
    private javax.swing.JCheckBox checkBard;
    private javax.swing.JCheckBox checkBasic;
    private javax.swing.JCheckBox checkBeast;
    private javax.swing.JCheckBox checkBerserk;
    private javax.swing.JCheckBox checkBerserker;
    private javax.swing.JCheckBox checkBlessing;
    private javax.swing.JCheckBox checkBlizzard;
    private javax.swing.JCheckBox checkBonMal;
    private javax.swing.JCheckBox checkBrave;
    private javax.swing.JCheckBox checkBulwark;
    private javax.swing.JCheckBox checkColossus;
    private javax.swing.JCheckBox checkCritical;
    private javax.swing.JCheckBox checkDC;
    private javax.swing.JCheckBox checkDais;
    private javax.swing.JCheckBox checkDeathstare;
    private javax.swing.JCheckBox checkDejavu;
    private javax.swing.JCheckBox checkDemon;
    private javax.swing.JCheckBox checkDfd;
    private javax.swing.JCheckBox checkDicex;
    private javax.swing.JCheckBox checkDodge;
    private javax.swing.JCheckBox checkDragon;
    private javax.swing.JCheckBox checkEV;
    private javax.swing.JCheckBox checkEX;
    private javax.swing.JCheckBox checkEclipse;
    private javax.swing.JCheckBox checkElfine;
    private javax.swing.JCheckBox checkEltarite;
    private javax.swing.JCheckBox checkFireball;
    private javax.swing.JCheckBox checkFrostbite;
    private javax.swing.JCheckBox checkGolem;
    private javax.swing.JCheckBox checkGuem;
    private javax.swing.JCheckBox checkHeal;
    private javax.swing.JCheckBox checkHit;
    private javax.swing.JCheckBox checkHomchai;
    private javax.swing.JCheckBox checkHuman;
    private javax.swing.JCheckBox checkIce;
    private javax.swing.JCheckBox checkIceElf;
    private javax.swing.JCheckBox checkIcy;
    private javax.swing.JCheckBox checkInspire;
    private javax.swing.JCheckBox checkKOT;
    private javax.swing.JCheckBox checkLD;
    private javax.swing.JCheckBox checkLightning;
    private javax.swing.JCheckBox checkM;
    private javax.swing.JCheckBox checkMDMG;
    private javax.swing.JCheckBox checkMER;
    private javax.swing.JCheckBox checkMage;
    private javax.swing.JCheckBox checkMarauder;
    private javax.swing.JCheckBox checkMimic;
    private javax.swing.JCheckBox checkNEH;
    private javax.swing.JCheckBox checkNOM;
    private javax.swing.JCheckBox checkNOZ;
    private javax.swing.JCheckBox checkNoble;
    private javax.swing.JCheckBox checkODC;
    private javax.swing.JCheckBox checkPA;
    private javax.swing.JCheckBox checkPIR;
    private javax.swing.JCheckBox checkPortal;
    private javax.swing.JCheckBox checkPowder;
    private javax.swing.JCheckBox checkPremium;
    private javax.swing.JCheckBox checkPriest;
    private javax.swing.JCheckBox checkPurify;
    private javax.swing.JCheckBox checkRL;
    private javax.swing.JCheckBox checkResilience;
    private javax.swing.JCheckBox checkRiposte;
    private javax.swing.JCheckBox checkRune;
    private javax.swing.JCheckBox checkRunic;
    private javax.swing.JCheckBox checkSB;
    private javax.swing.JCheckBox checkSH;
    private javax.swing.JCheckBox checkSL;
    private javax.swing.JCheckBox checkSTRDivide;
    private javax.swing.JCheckBox checkSTRDrain;
    private javax.swing.JCheckBox checkSTREquals;
    private javax.swing.JCheckBox checkSTRMinus;
    private javax.swing.JCheckBox checkSTRPlus;
    private javax.swing.JCheckBox checkSTRx;
    private javax.swing.JCheckBox checkSV;
    private javax.swing.JCheckBox checkScarab;
    private javax.swing.JCheckBox checkShield;
    private javax.swing.JCheckBox checkShieldBash;
    private javax.swing.JCheckBox checkShock;
    private javax.swing.JCheckBox checkSmite;
    private javax.swing.JCheckBox checkSolarian;
    private javax.swing.JCheckBox checkStench;
    private javax.swing.JCheckBox checkSupport;
    private javax.swing.JCheckBox checkSymbiosis;
    private javax.swing.JCheckBox checkT;
    private javax.swing.JCheckBox checkTEM;
    private javax.swing.JCheckBox checkTerror;
    private javax.swing.JCheckBox checkThorn;
    private javax.swing.JCheckBox checkThunder;
    private javax.swing.JCheckBox checkUndead;
    private javax.swing.JCheckBox checkUnkClass;
    private javax.swing.JCheckBox checkUnkRace;
    private javax.swing.JCheckBox checkVS;
    private javax.swing.JCheckBox checkWT;
    private javax.swing.JCheckBox checkWarrior;
    private javax.swing.JCheckBox checkXAlly;
    private javax.swing.JCheckBox checkXDice;
    private javax.swing.JCheckBox checkZIL;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel lbls;
    // End of variables declaration//GEN-END:variables
}
