package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.KeyStroke;
import javax.swing.SpinnerModel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainScreen extends javax.swing.JFrame {

    Object[] diceImgs = {createImageIcon("/IMG/DR_25.png"), createImageIcon("/IMG/DB_25.png"), createImageIcon("/IMG/DY_25.png"), createImageIcon("/IMG/DS_25.png")};
    Object[] guilds = {createImageIcon("/IMG/guildSmallMercenary.png"), createImageIcon("/IMG/guildSmallDesertNomads.png"), createImageIcon("/IMG/guildSmallKotoba.png"), createImageIcon("/IMG/guildSmallAvalonian.png"), createImageIcon("/IMG/guildSmallNehantists.png"), createImageIcon("/IMG/guildSmallNozdingardEnvoys.png"), createImageIcon("/IMG/guildSmallPirates.png"), createImageIcon("/IMG/guildSmallRunicLegion.png"), createImageIcon("/IMG/guildSmallSapHearts.png"), createImageIcon("/IMG/guildSmallStonelinkers.png"), createImageIcon("/IMG/guildSmallTempus.png"), createImageIcon("/IMG/guildSmallWT.png"), createImageIcon("/IMG/guildSmallZilWarriors.png")};
    Object[] classes = {createImageIcon("/IMG/classSmallUnknownClass.png"), createImageIcon("/IMG/classSmallBard.png"), createImageIcon("/IMG/classSmallBerserker.png"), createImageIcon("/IMG/classSmallColossus.png"), createImageIcon("/IMG/classSmallCraftsman.png"), createImageIcon("/IMG/classSmallMage.png"), createImageIcon("/IMG/classSmallMarauder.png"), createImageIcon("/IMG/classSmallPriest.png"), createImageIcon("/IMG/classSmallWarrior.png")};
    Object[] races = {createImageIcon("/IMG/raceSmallUnknownRace.png"), createImageIcon("/IMG/raceSmallBeast.png"), createImageIcon("/IMG/raceSmallDais.png"), createImageIcon("/IMG/raceSmallDemon.png"), createImageIcon("/IMG/raceSmallDragon.png"), createImageIcon("/IMG/raceSmallElfine.png"), createImageIcon("/IMG/raceSmallEltarite.png"), createImageIcon("/IMG/raceSmallGolem.png"), createImageIcon("/IMG/raceSmallGuemelite.png"), createImageIcon("/IMG/raceSmallHomchai.png"), createImageIcon("/IMG/raceSmallHuman.png"), createImageIcon("/IMG/raceSmallIceElf.png"), createImageIcon("/IMG/raceSmallSolarian.png"), createImageIcon("/IMG/raceSmallUndead.png")};
    Object[] diceNone = {createImageIcon("/IMG/empty.png"), createImageIcon("/IMG/DR_25.png"), createImageIcon("/IMG/DB_25.png"), createImageIcon("/IMG/DY_25.png"), createImageIcon("/IMG/DS_25.png")};

    String[][] orderingDamage = new String[84][2];
    String currentrow = "";
    String assuming = "";

    int[] nlblsB = {0, 0, 0, 0, 0};
    int[] nlblsM = {0, 0, 0, 0, 0};
    int[] nlblsT = {0, 0, 0, 0, 0};
    String[] dicesletter = {"R", "B", "Y", "S"};
    int diceLimit = 6;

    ArrayList<String> dispelList = new ArrayList<>();

    String road = "";
    String[] openarray = new String[125];
    String opening;
    String[][] skills = new String[][]{//Enabled,CheckAll,CheckOpp,CbbAny,cbbType,checktype,txtn,cbba,txtv,dc,dcd,sbc,cbbadd,txtadd,cbbope,cbbchange,count,txtanotherdmg,cbbanotherdmg,cbbanotherchange,shield
        {"yes", "f", "f", "0", "0", "f", "1", "Backstab", "0", "0", "0", "0", "No Add Effect", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
        {"not", "f", "f", "0", "0", "f", "1", "Backstab", "0", "0", "0", "0", "No Add Effect", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
        {"not", "f", "f", "0", "0", "f", "1", "Backstab", "0", "0", "0", "0", "No Add Effect", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
        {"yes", "f", "f", "0", "0", "f", "1", "Backstab", "0", "0", "0", "0", "No Add Effect", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
        {"not", "f", "f", "0", "0", "f", "1", "Backstab", "0", "0", "0", "0", "No Add Effect", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
        {"not", "f", "f", "0", "0", "f", "1", "Backstab", "0", "0", "0", "0", "No Add Effect", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
        {"yes", "f", "f", "0", "0", "f", "1", "Backstab", "0", "0", "0", "0", "No Add Effect", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
        {"not", "f", "f", "0", "0", "f", "1", "Backstab", "0", "0", "0", "0", "No Add Effect", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
        {"not", "f", "f", "0", "0", "f", "1", "Backstab", "0", "0", "0", "0", "No Add Effect", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"}};

    String[] eraoque = {"damaging", "damaging", "damaging", "damaging", "damaging", "damaging", "damaging", "damaging", "damaging"};
    int currentSkill = 0;

    int[] insp = {99, 99, 99, 99, 99, 99, 99, 99, 99};
    int[] alreadyConsumed = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    int shieldconsumed = 0;
    int zeroshield = 0;

    ArrayList<Integer> dicesbot = new ArrayList<>();
    ArrayList<Integer> dicesmid = new ArrayList<>();
    ArrayList<Integer> dicestop = new ArrayList<>();
    ArrayList<Integer> tresdados = new ArrayList<>();
    Roll utils = new Roll();
    int[] bot = new int[5];
    int[] mid = new int[5];
    int[] top = new int[5];
    String[] textbot = new String[5];
    String[] textmid = new String[5];
    String[] texttop = new String[5];
    int[] rollcalc = new int[6];
    int[] used = {0, 0, 0, 0, 0, 0};
    int[][] allrolls = {{0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 0, 2}, {0, 0, 0, 0, 0, 3}, {0, 0, 0, 0, 1, 1}, {0, 0, 0, 0, 1, 2}, {0, 0, 0, 0, 1, 3}, {0, 0, 0, 0, 2, 2}, {0, 0, 0, 0, 2, 3}, {0, 0, 0, 0, 3, 3}, {0, 0, 0, 1, 1, 1}, {0, 0, 0, 1, 1, 2}, {0, 0, 0, 1, 1, 3}, {0, 0, 0, 1, 2, 2}, {0, 0, 0, 1, 2, 3}, {0, 0, 0, 1, 3, 3}, {0, 0, 0, 2, 2, 2}, {0, 0, 0, 2, 2, 3}, {0, 0, 0, 2, 3, 3}, {0, 0, 0, 3, 3, 3}, {0, 0, 1, 1, 1, 1}, {0, 0, 1, 1, 1, 2}, {0, 0, 1, 1, 1, 3}, {0, 0, 1, 1, 2, 2}, {0, 0, 1, 1, 2, 3}, {0, 0, 1, 1, 3, 3}, {0, 0, 1, 2, 2, 2}, {0, 0, 1, 2, 2, 3}, {0, 0, 1, 2, 3, 3}, {0, 0, 1, 3, 3, 3}, {0, 0, 2, 2, 2, 2}, {0, 0, 2, 2, 2, 3}, {0, 0, 2, 2, 3, 3}, {0, 0, 2, 3, 3, 3}, {0, 0, 3, 3, 3, 3}, {0, 1, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 2}, {0, 1, 1, 1, 1, 3}, {0, 1, 1, 1, 2, 2}, {0, 1, 1, 1, 2, 3}, {0, 1, 1, 1, 3, 3}, {0, 1, 1, 2, 2, 2}, {0, 1, 1, 2, 2, 3}, {0, 1, 1, 2, 3, 3}, {0, 1, 1, 3, 3, 3}, {0, 1, 2, 2, 2, 2}, {0, 1, 2, 2, 2, 3}, {0, 1, 2, 2, 3, 3}, {0, 1, 2, 3, 3, 3}, {0, 1, 3, 3, 3, 3}, {0, 2, 2, 2, 2, 2}, {0, 2, 2, 2, 2, 3}, {0, 2, 2, 2, 3, 3}, {0, 2, 2, 3, 3, 3}, {0, 2, 3, 3, 3, 3}, {0, 3, 3, 3, 3, 3}, {1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 2}, {1, 1, 1, 1, 1, 3}, {1, 1, 1, 1, 2, 2}, {1, 1, 1, 1, 2, 3}, {1, 1, 1, 1, 3, 3}, {1, 1, 1, 2, 2, 2}, {1, 1, 1, 2, 2, 3}, {1, 1, 1, 2, 3, 3}, {1, 1, 1, 3, 3, 3}, {1, 1, 2, 2, 2, 2}, {1, 1, 2, 2, 2, 3}, {1, 1, 2, 2, 3, 3}, {1, 1, 2, 3, 3, 3}, {1, 1, 3, 3, 3, 3}, {1, 2, 2, 2, 2, 2}, {1, 2, 2, 2, 2, 3}, {1, 2, 2, 2, 3, 3}, {1, 2, 2, 3, 3, 3}, {1, 2, 3, 3, 3, 3}, {1, 3, 3, 3, 3, 3}, {2, 2, 2, 2, 2, 2}, {2, 2, 2, 2, 2, 3}, {2, 2, 2, 2, 3, 3}, {2, 2, 2, 3, 3, 3}, {2, 2, 3, 3, 3, 3}, {2, 3, 3, 3, 3, 3}, {3, 3, 3, 3, 3, 3}};
    int[][] all7throlls = {{0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 0, 0, 2}, {0, 0, 0, 0, 0, 0, 3}, {0, 0, 0, 0, 0, 1, 1}, {0, 0, 0, 0, 0, 1, 2}, {0, 0, 0, 0, 0, 1, 3}, {0, 0, 0, 0, 0, 2, 2}, {0, 0, 0, 0, 0, 2, 3}, {0, 0, 0, 0, 0, 3, 3}, {0, 0, 0, 0, 1, 1, 1}, {0, 0, 0, 0, 1, 1, 2}, {0, 0, 0, 0, 1, 1, 3}, {0, 0, 0, 0, 1, 2, 2}, {0, 0, 0, 0, 1, 2, 3}, {0, 0, 0, 0, 1, 3, 3}, {0, 0, 0, 0, 2, 2, 2}, {0, 0, 0, 0, 2, 2, 3}, {0, 0, 0, 0, 2, 3, 3}, {0, 0, 0, 0, 3, 3, 3}, {0, 0, 0, 1, 1, 1, 1}, {0, 0, 0, 1, 1, 1, 2}, {0, 0, 0, 1, 1, 1, 3}, {0, 0, 0, 1, 1, 2, 2}, {0, 0, 0, 1, 1, 2, 3}, {0, 0, 0, 1, 1, 3, 3}, {0, 0, 0, 1, 2, 2, 2}, {0, 0, 0, 1, 2, 2, 3}, {0, 0, 0, 1, 2, 3, 3}, {0, 0, 0, 1, 3, 3, 3}, {0, 0, 0, 2, 2, 2, 2}, {0, 0, 0, 2, 2, 2, 3}, {0, 0, 0, 2, 2, 3, 3}, {0, 0, 0, 2, 3, 3, 3}, {0, 0, 0, 3, 3, 3, 3}, {0, 0, 1, 1, 1, 1, 1}, {0, 0, 1, 1, 1, 1, 2}, {0, 0, 1, 1, 1, 1, 3}, {0, 0, 1, 1, 1, 2, 2}, {0, 0, 1, 1, 1, 2, 3}, {0, 0, 1, 1, 1, 3, 3}, {0, 0, 1, 1, 2, 2, 2}, {0, 0, 1, 1, 2, 2, 3}, {0, 0, 1, 1, 2, 3, 3}, {0, 0, 1, 1, 3, 3, 3}, {0, 0, 1, 2, 2, 2, 2}, {0, 0, 1, 2, 2, 2, 3}, {0, 0, 1, 2, 2, 3, 3}, {0, 0, 1, 2, 3, 3, 3}, {0, 0, 1, 3, 3, 3, 3}, {0, 0, 2, 2, 2, 2, 2}, {0, 0, 2, 2, 2, 2, 3}, {0, 0, 2, 2, 2, 3, 3}, {0, 0, 2, 2, 3, 3, 3}, {0, 0, 2, 3, 3, 3, 3}, {0, 0, 3, 3, 3, 3, 3}, {0, 1, 1, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 1, 2}, {0, 1, 1, 1, 1, 1, 3}, {0, 1, 1, 1, 1, 2, 2}, {0, 1, 1, 1, 1, 2, 3}, {0, 1, 1, 1, 1, 3, 3}, {0, 1, 1, 1, 2, 2, 2}, {0, 1, 1, 1, 2, 2, 3}, {0, 1, 1, 1, 2, 3, 3}, {0, 1, 1, 1, 3, 3, 3}, {0, 1, 1, 2, 2, 2, 2}, {0, 1, 1, 2, 2, 2, 3}, {0, 1, 1, 2, 2, 3, 3}, {0, 1, 1, 2, 3, 3, 3}, {0, 1, 1, 3, 3, 3, 3}, {0, 1, 2, 2, 2, 2, 2}, {0, 1, 2, 2, 2, 2, 3}, {0, 1, 2, 2, 2, 3, 3}, {0, 1, 2, 2, 3, 3, 3}, {0, 1, 2, 3, 3, 3, 3}, {0, 1, 3, 3, 3, 3, 3}, {0, 2, 2, 2, 2, 2, 2}, {0, 2, 2, 2, 2, 2, 3}, {0, 2, 2, 2, 2, 3, 3}, {0, 2, 2, 2, 3, 3, 3}, {0, 2, 2, 3, 3, 3, 3}, {0, 2, 3, 3, 3, 3, 3}, {0, 3, 3, 3, 3, 3, 3}, {1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 2}, {1, 1, 1, 1, 1, 1, 3}, {1, 1, 1, 1, 1, 2, 2}, {1, 1, 1, 1, 1, 2, 3}, {1, 1, 1, 1, 1, 3, 3}, {1, 1, 1, 1, 2, 2, 2}, {1, 1, 1, 1, 2, 2, 3}, {1, 1, 1, 1, 2, 3, 3}, {1, 1, 1, 1, 3, 3, 3}, {1, 1, 1, 2, 2, 2, 2}, {1, 1, 1, 2, 2, 2, 3}, {1, 1, 1, 2, 2, 3, 3}, {1, 1, 1, 2, 3, 3, 3}, {1, 1, 1, 3, 3, 3, 3}, {1, 1, 2, 2, 2, 2, 2}, {1, 1, 2, 2, 2, 2, 3}, {1, 1, 2, 2, 2, 3, 3}, {1, 1, 2, 2, 3, 3, 3}, {1, 1, 2, 3, 3, 3, 3}, {1, 1, 3, 3, 3, 3, 3}, {1, 2, 2, 2, 2, 2, 2}, {1, 2, 2, 2, 2, 2, 3}, {1, 2, 2, 2, 2, 3, 3}, {1, 2, 2, 2, 3, 3, 3}, {1, 2, 2, 3, 3, 3, 3}, {1, 2, 3, 3, 3, 3, 3}, {1, 3, 3, 3, 3, 3, 3}, {2, 2, 2, 2, 2, 2, 2}, {2, 2, 2, 2, 2, 2, 3}, {2, 2, 2, 2, 2, 3, 3}, {2, 2, 2, 2, 3, 3, 3}, {2, 2, 2, 3, 3, 3, 3}, {2, 2, 3, 3, 3, 3, 3}, {2, 3, 3, 3, 3, 3, 3}, {3, 3, 3, 3, 3, 3, 3}};
    int[][] alltransrolls = {{0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 0, 2}, {0, 0, 0, 0, 1, 1}, {0, 0, 0, 0, 1, 2}, {0, 0, 0, 0, 2, 2}, {0, 0, 0, 1, 1, 1}, {0, 0, 0, 1, 1, 2}, {0, 0, 0, 1, 2, 2}, {0, 0, 0, 2, 2, 2}, {0, 0, 1, 1, 1, 1}, {0, 0, 1, 1, 1, 2}, {0, 0, 1, 1, 2, 2}, {0, 0, 1, 2, 2, 2}, {0, 0, 2, 2, 2, 2}, {0, 1, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 2}, {0, 1, 1, 1, 2, 2}, {0, 1, 1, 2, 2, 2}, {0, 1, 2, 2, 2, 2}, {0, 2, 2, 2, 2, 2}, {1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 2}, {1, 1, 1, 1, 2, 2}, {1, 1, 1, 2, 2, 2}, {1, 1, 2, 2, 2, 2}, {1, 2, 2, 2, 2, 2}, {2, 2, 2, 2, 2, 2}};
    int[][] all7thtransrolls = {{0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 0, 0, 2}, {0, 0, 0, 0, 0, 1, 1}, {0, 0, 0, 0, 0, 1, 2}, {0, 0, 0, 0, 0, 2, 2}, {0, 0, 0, 0, 1, 1, 1}, {0, 0, 0, 0, 1, 1, 2}, {0, 0, 0, 0, 1, 2, 2}, {0, 0, 0, 0, 2, 2, 2}, {0, 0, 0, 1, 1, 1, 1}, {0, 0, 0, 1, 1, 1, 2}, {0, 0, 0, 1, 1, 2, 2}, {0, 0, 0, 1, 2, 2, 2}, {0, 0, 0, 2, 2, 2, 2}, {0, 0, 1, 1, 1, 1, 1}, {0, 0, 1, 1, 1, 1, 2}, {0, 0, 1, 1, 1, 2, 2}, {0, 0, 1, 1, 2, 2, 2}, {0, 0, 1, 2, 2, 2, 2}, {0, 0, 2, 2, 2, 2, 2}, {0, 1, 1, 1, 1, 1, 1}, {0, 1, 1, 1, 1, 1, 2}, {0, 1, 1, 1, 1, 2, 2}, {0, 1, 1, 1, 2, 2, 2}, {0, 1, 1, 2, 2, 2, 2}, {0, 1, 2, 2, 2, 2, 2}, {0, 2, 2, 2, 2, 2, 2}, {1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1, 2}, {1, 1, 1, 1, 1, 2, 2}, {1, 1, 1, 1, 2, 2, 2}, {1, 1, 1, 2, 2, 2, 2}, {1, 1, 2, 2, 2, 2, 2}, {1, 2, 2, 2, 2, 2, 2}, {2, 2, 2, 2, 2, 2, 2}};
    String rollsDices = "";
    String labeling[] = new String[9];

    int actbot = 0;
    int acttop = 0;
    int actmid = 0;
    int times[] = {1, 1, 1, 1, 1, 1, 1, 1, 1};

    String guild = "Merc";
    String oppguild = "Merc";
    String clas = "UnkClass";
    String oppclas = "UnkClass";
    String race = "UnkRace";
    String opprace = "UnkRace";
    String every[] = {"Any", "Ava", "DN", "Kot", "Merc", "Neh", "Noz", "Pir", "RL", "SH", "SL", "Temp", "WT", "Zil", "Bard", "Berserker", "Colossus", "Craftsman", "Mage", "Marauder", "Priest", "UnkClass", "Warrior", "Beast", "Dais", "Demon", "Dragon", "Elfine", "Eltarite", "Golem", "Guem", "Homchai", "Human", "Ice Elf", "Solarian", "Undead", "UnkRace"};

    int total = 0;
    int damage = 0;
    int thorndamage = 0;
    int fbdamage = 0;

    int mmcounter = 0;
    int ptcounter = 0;
    int haveinspire = 0;
    int totalscarab = 0;
    int sbcounter = 0;
    int oppminusstr = 0;
    int djcounter = 0;
    int oldblizzard = 0;
    int prcounter = 0;
    int blizcon = 0;
    int dispelcounter = 0;
    int heal = 0;

    int berserk = 0;
    int blessing = 0;
    int blizzard = 0;
    int bulwark = 0;
    int critical = 0;
    int moredamage = 0;
    int dodge = 0;
    int eclipse = 0;
    int frostbite = 0;
    int fatigue = 0;
    int ice = 0;
    int malus = 0;
    int powder = 0;
    int str = 0;
    int resilience = 0;
    int rage = 0;
    int storm = 0;
    int scarab = 0;
    int runes = 0;
    int riposte = 0;
    int shield = 0;
    int stench = 0;
    int terror = 0;
    int thorn = 0;
    int thunderstruck = 0;
    int shadow = 0;
    int pierce = 0;
    int oppberserk = 0;
    int oppblessing = 0;
    int oppblizzard = 0;
    int oppbulwark = 0;
    int oppcritical = 0;
    int oppmoredamage = 0;
    int oppdodge = 0;
    int oppfrostbite = 0;
    int oppfatigue = 0;
    int oppice = 0;
    int oppmalus = 0;
    int oppPowder = 0;
    int oppstr = 0;
    int oppresilience = 0;
    int opprage = 0;
    int oppstorm = 0;
    int oppscarab = 0;
    int opprunes = 0;
    int oppriposte = 0;
    int oppshield = 0;
    int oppstench = 0;
    int oppterror = 0;
    int oppthorn = 0;
    int oppthunderstruck = 0;
    int oppdvdcounter = 1;
    int oppeclipse = 0;
    int oppequalcounter = -1;
    int oppshadow = 0;
    int oppPierce = 0;

    int all[] = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    int fixeall[] = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    int moreif[] = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    int fixemoreif[] = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    int vallist[] = new int[9];

    String details = "";
    String alldetails = "";
    String setmade = "";

    public MainScreen(String savedset, String path) {
        initComponents();
        centralizarComponente();

        road = path;

        lblLogo.requestFocus();

        String actionKey = "theAction";
        KeyStroke stroke = KeyStroke.getKeyStroke("ctrl S");
        Action action = new MyActionListener("Action Didn't Happen", btnSave);
        InputMap inputMap = btnSave.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(stroke, actionKey);
        ActionMap actionMap = btnSave.getActionMap();
        actionMap.put(actionKey, action);

        actionKey = "theAction";
        stroke = KeyStroke.getKeyStroke("ctrl O");
        action = new MyActionListener("Action Didn't Happen", btnOpen);
        inputMap = btnOpen.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(stroke, actionKey);
        actionMap = btnOpen.getActionMap();
        actionMap.put(actionKey, action);

        actionKey = "theAction";
        stroke = KeyStroke.getKeyStroke("ctrl L");
        action = new MyActionListener("Action Didn't Happen", btnLoad);
        inputMap = btnLoad.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(stroke, actionKey);
        actionMap = btnLoad.getActionMap();
        actionMap.put(actionKey, action);

        actionKey = "theAction";
        stroke = KeyStroke.getKeyStroke("ctrl R");
        action = new MyActionListener("Action Didn't Happen", btnReset);
        inputMap = btnReset.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(stroke, actionKey);
        actionMap = btnReset.getActionMap();
        actionMap.put(actionKey, action);

        actionKey = "theAction";
        stroke = KeyStroke.getKeyStroke("ENTER");
        action = new MyActionListener("Action Didn't Happen", btnAll);
        inputMap = btnAll.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(stroke, actionKey);
        actionMap = btnAll.getActionMap();
        actionMap.put(actionKey, action);

        actionKey = "theAction";
        stroke = KeyStroke.getKeyStroke("shift ENTER");
        action = new MyActionListener("Action Didn't Happen", btnCalc);
        inputMap = btnCalc.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(stroke, actionKey);
        actionMap = btnCalc.getActionMap();
        actionMap.put(actionKey, action);

        pBuffing.setVisible(false);
        pAssuming.setVisible(false);
        pSkill.setVisible(false);
        pAuraSeal.setVisible(false);

        D7.setVisible(false);
        BlessingSeal.setVisible(false);

        pT2.setVisible(false);
        pT3.setVisible(false);
        pM2.setVisible(false);
        pM3.setVisible(false);
        pB2.setVisible(false);
        pB3.setVisible(false);

        checkSymb.setVisible(false);
        checkAll.setVisible(false);
        checkOpp.setVisible(false);
        cbbAny.setVisible(false);
        checkType.setVisible(false);
        lblbuffdebuff.setVisible(false);
        counterBuffDebuff.setVisible(false);
        cbbDiceChange.setVisible(false);
        lblTo.setVisible(false);
        cbbDiceChanged.setVisible(false);
        txtAddDMG.setVisible(false);
        cbbOperation.setVisible(false);
        cbbChange.setVisible(false);
        lblCounter.setVisible(false);
        counter.setVisible(false);
        lblAnotherWall.setVisible(false);
        lblAddAnother.setVisible(false);
        cbbAnotherAddDMG.setVisible(false);
        txtAnotherAddDMG.setVisible(false);
        cbbAnotherOperation.setVisible(false);
        lblShieldbash.setVisible(false);
        cbbShieldbash.setVisible(false);
        lblSupport.setVisible(false);
        lblCounter1.setVisible(false);
        counter1.setVisible(false);
        lblWL.setVisible(false);
        rbtWinning.setVisible(false);
        rbtLosing.setVisible(false);
        txtShield.setVisible(false);

        if (!"".equals(savedset)) {
            openizando(savedset);
        }

        minimando();
        updateLabels();
    }

    protected static ImageIcon createImageIcon(String path) {
        URL imgURL = MainScreen.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    private void centralizarComponente() {
        Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dw = getSize();
        setLocation((ds.width - dw.width) / 2, (ds.height - dw.height) / 2);
    }

    private void showSkill() {
        pBuffing.setVisible(false);
        pAssuming.setVisible(false);
        pSkill.setVisible(true);
        pAuraSeal.setVisible(false);
    }

    private void pickingSkill(int whatSkill) {

        currentSkill = whatSkill;

        String aux[] = new String[23];
        System.arraycopy(skills[currentSkill], 0, aux, 0, 23);

        cbbA.setSelectedItem(aux[7]);
        if ("t".equals(aux[1])) {
            checkAll.setSelected(true);
        } else {
            checkAll.setSelected(false);
        }
        if ("t".equals(aux[2])) {
            checkOpp.setSelected(true);
        } else {
            checkOpp.setSelected(false);
        }
        cbbAny.setSelectedIndex(Integer.parseInt(aux[3]));
        cbbType.setSelectedIndex(Integer.parseInt(aux[4]));
        if ("t".equals(aux[5])) {
            checkType.setSelected(true);
        } else {
            checkType.setSelected(false);
        }
        txtN.setText(aux[6]);
        if (!aux[7].equals("Dice Change") && !aux[7].equals("Physical Attack") && !aux[7].equals("Deja Vu") && !aux[7].equals("Inspire") && !aux[7].equals("Symbiosis")) {
            txtV.setVisible(true);
        }
        txtV.setText(aux[8]);
        if (!aux[7].equals("Dice Change")) {
            cbbDiceChange.setVisible(false);
            cbbDiceChanged.setVisible(false);
            lblTo.setVisible(false);
        }
        cbbDiceChange.setSelectedIndex(Integer.parseInt(aux[9]));
        cbbDiceChanged.setSelectedIndex(Integer.parseInt(aux[10]));

        cbbShieldbash.setSelectedIndex(Integer.parseInt(aux[11]));
        System.out.println(aux[12]);
        cbbAddDMG.setSelectedItem(aux[12]);
        txtAddDMG.setText(aux[13]);
        mudandoOperation(aux[12]);
        cbbOperation.setSelectedItem(aux[14]);
        cbbChange.setSelectedItem(aux[15]);
        counter.setValue(Integer.parseInt(aux[16]));
        cbbAnotherAddDMG.setSelectedIndex(Integer.parseInt(aux[17]));
        txtAnotherAddDMG.setText(aux[18]);
        cbbAnotherOperation.setSelectedIndex(Integer.parseInt(aux[19]));
        counterBuffDebuff.setValue(Integer.parseInt(aux[21]));
        txtShield.setText(aux[22]);

        savingSkill();

    }

    private void savingSkill() {
        //Enabled,CheckAll,CheckOpp,CbbAny,cbbType,checktype,txtn,cbba,txtv,dc,dcd,sbc,cbbadd,txtadd,cbbope,cbbchange,count,txtanotherdmg,cbbanotherdmg,cbbanotherchange
        minimando();

        if (checkAll.isSelected()) {
            skills[currentSkill][1] = "t";
        } else {
            skills[currentSkill][1] = "f";
        }
        if (checkOpp.isSelected()) {
            skills[currentSkill][2] = "t";
        } else {
            skills[currentSkill][2] = "f";
        }
        skills[currentSkill][3] = cbbAny.getSelectedIndex() + "";
        skills[currentSkill][4] = cbbType.getSelectedIndex() + "";
        if (checkType.isSelected()) {
            skills[currentSkill][5] = "t";
        } else {
            skills[currentSkill][5] = "f";
        }
        skills[currentSkill][6] = txtN.getText();
        skills[currentSkill][7] = cbbA.getSelectedItem().toString();
        skills[currentSkill][8] = txtV.getText();
        skills[currentSkill][9] = cbbDiceChange.getSelectedIndex() + "";
        skills[currentSkill][10] = cbbDiceChanged.getSelectedIndex() + "";
        skills[currentSkill][11] = cbbShieldbash.getSelectedIndex() + "";
        skills[currentSkill][12] = cbbAddDMG.getSelectedItem().toString();
        skills[currentSkill][13] = txtAddDMG.getText();
        skills[currentSkill][14] = cbbOperation.getSelectedItem().toString();
        skills[currentSkill][15] = cbbChange.getSelectedItem().toString();
        skills[currentSkill][16] = counter.getValue().toString();
        skills[currentSkill][17] = cbbAnotherAddDMG.getSelectedIndex() + "";
        skills[currentSkill][18] = txtAnotherAddDMG.getText();
        skills[currentSkill][19] = cbbAnotherOperation.getSelectedIndex() + "";
        skills[currentSkill][20] = cbbChange.getSelectedIndex() + "";
        skills[currentSkill][21] = counterBuffDebuff.getValue().toString();
        skills[currentSkill][22] = txtShield.getText();

        updateLabels();
    }

    private void updateLabels() {
        for (int t = 0; t < 9; t++) {
            labeling[t] = "";
            if ("yes".equals(skills[t][0])) {

                switch (eraoque[t]) {
                    case "dispelling":
                        labeling[t] += "Dispel " + skills[t][21];
                        if ("only VS".equals(skills[t][14])) {
                            labeling[t] += " VS " + skills[t][15];
                        }
                        if ("only if atk".equals(skills[t][14])) {
                            labeling[t] += " if atk";
                        }
                        if ("only if dfd".equals(skills[t][14])) {
                            labeling[t] += " if dfd";
                        }
                        break;
                    case "buffing":
                        if ("t".equals(skills[t][1])) {
                            labeling[t] += "All ";
                            if ("t".equals(skills[t][2])) {
                                labeling[t] += "opp ";
                            }
                            if (!"0".equals(skills[t][3])) {
                                labeling[t] += cbbAny.getItemAt(Integer.parseInt(skills[t][3])) + " ";
                            }
                        } else {
                            if ("t".equals(skills[t][2])) {
                                labeling[t] += "opp ";
                            }
                        }

                        switch (skills[t][12]) {
                            case "No Add Effect":
                                if (!"Rune".equals(skills[t][7])) {
                                    if (!"0".equals(skills[t][4])) {
                                        labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                    }
                                    labeling[t] += skills[t][7] + " " + skills[t][8];
                                } else {
                                    if (!"0".equals(skills[t][4])) {
                                        labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                    }
                                    if (Integer.parseInt(skills[t][8]) > 1) {
                                        labeling[t] += skills[t][8] + " Runes";
                                    } else {
                                        labeling[t] += skills[t][8] + " Rune";
                                    }
                                }
                                break;
                            case "Dice":
                                switch (skills[t][14]) {
                                    case "[VAL] x":
                                        if (!"Rune".equals(skills[t][7])) {
                                            if (!"0".equals(skills[t][4])) {
                                                labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                            }
                                            labeling[t] += skills[t][7] + " " + skills[t][8] + " x " + skills[t][15].substring(0, 1);
                                        } else {
                                            if (!"0".equals(skills[t][4])) {
                                                labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                            }
                                            if (Integer.parseInt(skills[t][8]) > 1) {
                                                labeling[t] += skills[t][8] + " Runes x " + skills[t][15].substring(0, 1);
                                            } else {
                                                labeling[t] += skills[t][8] + " Rune x " + skills[t][15].substring(0, 1);
                                            }
                                        }
                                        break;
                                    case "+[VAL] per":
                                        if (!"Rune".equals(skills[t][7])) {
                                            if (!"0".equals(skills[t][4])) {
                                                labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                            }
                                            labeling[t] += skills[t][7] + " " + skills[t][8] + " +" + skills[t][13] + " x " + skills[t][15].substring(0, 1);
                                        } else {
                                            if (!"0".equals(skills[t][4])) {
                                                labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                            }
                                            if (Integer.parseInt(skills[t][8]) > 1) {
                                                labeling[t] += skills[t][8] + " Runes +" + skills[t][13] + " x " + skills[t][15].substring(0, 1);
                                            } else {
                                                labeling[t] += skills[t][8] + " Rune +" + skills[t][13] + " x " + skills[t][15].substring(0, 1);
                                            }
                                        }
                                        break;
                                    case "-[VAL] per":
                                        if (!"Rune".equals(skills[t][7])) {
                                            if (!"0".equals(skills[t][4])) {
                                                labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                            }
                                            labeling[t] += skills[t][7] + " " + skills[t][8] + " -" + skills[t][13] + " x " + skills[t][15].substring(0, 1);
                                        } else {
                                            if (!"0".equals(skills[t][4])) {
                                                labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                            }
                                            if (Integer.parseInt(skills[t][8]) > 1) {
                                                labeling[t] += skills[t][8] + " Runes -" + skills[t][13] + " x " + skills[t][15].substring(0, 1);
                                            } else {
                                                labeling[t] += skills[t][8] + " Rune -" + skills[t][13] + " x " + skills[t][15].substring(0, 1);
                                            }
                                        }
                                        break;
                                }
                                break;
                            case "Opp Dice":
                                switch (skills[t][14]) {
                                    case "[VAL] x":
                                        if (!"Rune".equals(skills[t][7])) {
                                            if (!"0".equals(skills[t][4])) {
                                                labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                            }
                                            labeling[t] += skills[t][7] + " " + skills[t][8] + " x opp " + skills[t][15].substring(0, 1);
                                        } else {
                                            if (!"0".equals(skills[t][4])) {
                                                labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                            }
                                            if (Integer.parseInt(skills[t][8]) > 1) {
                                                labeling[t] += skills[t][8] + " Runes x opp " + skills[t][15].substring(0, 1);
                                            } else {
                                                labeling[t] += skills[t][8] + " Rune x opp " + skills[t][15].substring(0, 1);
                                            }
                                        }
                                        break;
                                    case "+[VAL] per":
                                        if (!"Rune".equals(skills[t][7])) {
                                            if (!"0".equals(skills[t][4])) {
                                                labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                            }
                                            labeling[t] += skills[t][7] + " " + skills[t][8] + " +" + skills[t][13] + " x opp " + skills[t][15].substring(0, 1);
                                        } else {
                                            if (!"0".equals(skills[t][4])) {
                                                labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                            }
                                            if (Integer.parseInt(skills[t][8]) > 1) {
                                                labeling[t] += skills[t][8] + " Runes +" + skills[t][13] + " x opp " + skills[t][15].substring(0, 1);
                                            } else {
                                                labeling[t] += skills[t][8] + " Rune +" + skills[t][13] + " x opp " + skills[t][15].substring(0, 1);
                                            }
                                        }
                                        break;
                                    case "-[VAL] per":
                                        if (!"Rune".equals(skills[t][7])) {
                                            if (!"0".equals(skills[t][4])) {
                                                labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                            }
                                            labeling[t] += skills[t][7] + " " + skills[t][8] + " -" + skills[t][13] + " x opp " + skills[t][15].substring(0, 1);
                                        } else {
                                            if (!"0".equals(skills[t][4])) {
                                                labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                            }
                                            if (Integer.parseInt(skills[t][8]) > 1) {
                                                labeling[t] += skills[t][8] + " Runes -" + skills[t][13] + " x opp " + skills[t][15].substring(0, 1);
                                            } else {
                                                labeling[t] += skills[t][8] + " Rune -" + skills[t][13] + " x opp " + skills[t][15].substring(0, 1);
                                            }
                                        }
                                        break;
                                }
                                break;
                            case "VS":
                                switch (skills[t][14]) {
                                    case "[VAL] VS":
                                        if (!"Rune".equals(skills[t][7])) {
                                            if (!"0".equals(skills[t][4])) {
                                                labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                            }
                                            labeling[t] += skills[t][7] + " " + skills[t][8] + ", " + skills[t][13] + " VS " + skills[t][15];

                                        } else {
                                            if (!"0".equals(skills[t][4])) {
                                                labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                            }
                                            if (Integer.parseInt(skills[t][8]) > 1) {
                                                labeling[t] += skills[t][8] + " Runes, " + skills[t][13] + " VS " + skills[t][15];
                                            } else {
                                                labeling[t] += skills[t][8] + " Rune, " + skills[t][13] + " VS " + skills[t][15];
                                            }
                                        }
                                        break;
                                    case "only VS":
                                        if (!"Rune".equals(skills[t][7])) {
                                            if (!"0".equals(skills[t][4])) {
                                                labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                            }
                                            labeling[t] += skills[t][7] + " " + skills[t][8] + " VS " + skills[t][15];
                                        } else {
                                            if (!"0".equals(skills[t][4])) {
                                                labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                            }
                                            if (Integer.parseInt(skills[t][8]) > 1) {
                                                labeling[t] += skills[t][8] + " Runes VS " + skills[t][15];
                                            } else {
                                                labeling[t] += skills[t][8] + " Rune VS " + skills[t][15];
                                            }
                                        }
                                        break;
                                }
                                break;
                            case "Ally":
                                switch (skills[t][14]) {
                                    case "[VAL] x":
                                        if (!"Rune".equals(skills[t][7])) {
                                            if (!"0".equals(skills[t][4])) {
                                                labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                            }
                                            if (!"Ally".equals(skills[t][15])) {
                                                labeling[t] += skills[t][7] + " " + skills[t][8] + " x " + skills[t][15] + " ally";
                                            } else {
                                                labeling[t] += skills[t][7] + " " + skills[t][8] + " x " + skills[t][15];
                                            }
                                        } else {
                                            if (!"0".equals(skills[t][4])) {
                                                labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                            }
                                            if (Integer.parseInt(skills[t][8]) > 1) {
                                                if (!"Ally".equals(skills[t][15])) {
                                                    labeling[t] += skills[t][8] + " Runes x " + skills[t][15] + " ally";
                                                } else {
                                                    labeling[t] += skills[t][8] + " Runes x " + skills[t][15];
                                                }
                                            } else {
                                                if (!"Ally".equals(skills[t][15])) {
                                                    labeling[t] += skills[t][8] + " Rune x " + skills[t][15] + " ally";
                                                } else {
                                                    labeling[t] += skills[t][8] + " Rune x " + skills[t][15];
                                                }
                                            }
                                        }
                                        break;
                                    case "+[VAL] per":
                                        if (!"Rune".equals(skills[t][7])) {
                                            if (!"0".equals(skills[t][4])) {
                                                labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                            }
                                            if (!"Ally".equals(skills[t][15])) {
                                                labeling[t] += skills[t][7] + " " + skills[t][8] + " +" + skills[t][13] + " x " + skills[t][15] + " ally";
                                            } else {
                                                labeling[t] += skills[t][7] + " " + skills[t][8] + " +" + skills[t][13] + " x " + skills[t][15];
                                            }
                                        } else {
                                            if (!"0".equals(skills[t][4])) {
                                                labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                            }
                                            if (Integer.parseInt(skills[t][8]) > 1) {
                                                if (!"Ally".equals(skills[t][15])) {
                                                    labeling[t] += skills[t][8] + " Runes +" + skills[t][13] + " x " + skills[t][15] + " ally";
                                                } else {
                                                    labeling[t] += skills[t][8] + " Runes +" + skills[t][13] + " x " + skills[t][15];
                                                }
                                            } else {
                                                if (!"Ally".equals(skills[t][15])) {
                                                    labeling[t] += skills[t][8] + " Rune +" + skills[t][13] + " x " + skills[t][15] + " ally";
                                                } else {
                                                    labeling[t] += skills[t][8] + " Rune +" + skills[t][13] + " x " + skills[t][15];
                                                }
                                            }
                                        }
                                        break;
                                    case "-[VAL] per":
                                        if (!"Rune".equals(skills[t][7])) {
                                            if (!"0".equals(skills[t][4])) {
                                                labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                            }
                                            if (!"Ally".equals(skills[t][15])) {
                                                labeling[t] += skills[t][7] + " " + skills[t][8] + " -" + skills[t][13] + " x " + skills[t][15] + " ally";
                                            } else {
                                                labeling[t] += skills[t][7] + " " + skills[t][8] + " -" + skills[t][13] + " x " + skills[t][15];
                                            }
                                        } else {
                                            if (!"0".equals(skills[t][4])) {
                                                labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                            }
                                            if (Integer.parseInt(skills[t][8]) > 1) {
                                                if (!"Ally".equals(skills[t][15])) {
                                                    labeling[t] += skills[t][8] + " Runes -" + skills[t][13] + " x " + skills[t][15] + " ally";
                                                } else {
                                                    labeling[t] += skills[t][8] + " Runes -" + skills[t][13] + " x " + skills[t][15];
                                                }
                                            } else {
                                                if (!"Ally".equals(skills[t][15])) {
                                                    labeling[t] += skills[t][8] + " Rune -" + skills[t][13] + " x " + skills[t][15] + " ally";
                                                } else {
                                                    labeling[t] += skills[t][8] + " Rune -" + skills[t][13] + " x " + skills[t][15];
                                                }
                                            }
                                        }
                                        break;
                                }
                                break;
                            case "Opp":
                                switch (skills[t][14]) {
                                    case "[VAL] x":
                                        if (!"Rune".equals(skills[t][7])) {
                                            if (!"0".equals(skills[t][4])) {
                                                labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                            }
                                            if (!"Ally".equals(skills[t][15])) {
                                                labeling[t] += skills[t][7] + " " + skills[t][8] + " x " + skills[t][15] + " opp";
                                            } else {
                                                labeling[t] += skills[t][7] + " " + skills[t][8] + " x " + skills[t][15];
                                            }
                                        } else {
                                            if (!"0".equals(skills[t][4])) {
                                                labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                            }
                                            if (Integer.parseInt(skills[t][8]) > 1) {
                                                if (!"Ally".equals(skills[t][15])) {
                                                    labeling[t] += skills[t][8] + " Runes x " + skills[t][15] + " opp";
                                                } else {
                                                    labeling[t] += skills[t][8] + " Runes x " + skills[t][15];
                                                }
                                            } else {
                                                if (!"Ally".equals(skills[t][15])) {
                                                    labeling[t] += skills[t][8] + " Rune x " + skills[t][15] + " opp";
                                                } else {
                                                    labeling[t] += skills[t][8] + " Rune x " + skills[t][15];
                                                }
                                            }
                                        }
                                        break;
                                    case "+[VAL] per":
                                        if (!"Rune".equals(skills[t][7])) {
                                            if (!"0".equals(skills[t][4])) {
                                                labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                            }
                                            if (!"Ally".equals(skills[t][15])) {
                                                labeling[t] += skills[t][7] + " " + skills[t][8] + " +" + skills[t][13] + " x " + skills[t][15] + " opp";
                                            } else {
                                                labeling[t] += skills[t][7] + " " + skills[t][8] + " +" + skills[t][13] + " x " + skills[t][15];
                                            }
                                        } else {
                                            if (!"0".equals(skills[t][4])) {
                                                labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                            }
                                            if (Integer.parseInt(skills[t][8]) > 1) {
                                                if (!"Ally".equals(skills[t][15])) {
                                                    labeling[t] += skills[t][8] + " Runes +" + skills[t][13] + " x " + skills[t][15] + " opp";
                                                } else {
                                                    labeling[t] += skills[t][8] + " Runes +" + skills[t][13] + " x " + skills[t][15];
                                                }
                                            } else {
                                                if (!"Ally".equals(skills[t][15])) {
                                                    labeling[t] += skills[t][8] + " Rune +" + skills[t][13] + " x " + skills[t][15] + " opp";
                                                } else {
                                                    labeling[t] += skills[t][8] + " Rune +" + skills[t][13] + " x " + skills[t][15];
                                                }
                                            }
                                        }
                                        break;
                                    case "-[VAL] per":
                                        if (!"Rune".equals(skills[t][7])) {
                                            if (!"0".equals(skills[t][4])) {
                                                labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                            }
                                            if (!"Ally".equals(skills[t][15])) {
                                                labeling[t] += skills[t][7] + " " + skills[t][8] + " -" + skills[t][13] + " x " + skills[t][15] + " opp";
                                            } else {
                                                labeling[t] += skills[t][7] + " " + skills[t][8] + " -" + skills[t][13] + " x " + skills[t][15];
                                            }
                                        } else {
                                            if (!"0".equals(skills[t][4])) {
                                                labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                            }
                                            if (Integer.parseInt(skills[t][8]) > 1) {
                                                if (!"Ally".equals(skills[t][15])) {
                                                    labeling[t] += skills[t][8] + " Runes -" + skills[t][13] + " x " + skills[t][15] + " opp";
                                                } else {
                                                    labeling[t] += skills[t][8] + " Runes -" + skills[t][13] + " x " + skills[t][15];
                                                }
                                            } else {
                                                if (!"Ally".equals(skills[t][15])) {
                                                    labeling[t] += skills[t][8] + " Rune -" + skills[t][13] + " x " + skills[t][15] + " opp";
                                                } else {
                                                    labeling[t] += skills[t][8] + " Rune -" + skills[t][13] + " x " + skills[t][15];
                                                }
                                            }
                                        }
                                        break;
                                }
                                break;
                            case "Attacker":
                                switch (skills[t][14]) {
                                    case "[VAL] if atk":
                                        if (!"Rune".equals(skills[t][7])) {
                                            if (!"0".equals(skills[t][4])) {
                                                labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                            }
                                            labeling[t] += skills[t][7] + " " + skills[t][8] + ", " + skills[t][13] + " if atk";
                                        } else {
                                            if (!"0".equals(skills[t][4])) {
                                                labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                            }
                                            if (Integer.parseInt(skills[t][8]) > 1) {
                                                labeling[t] += skills[t][8] + " Runes, " + skills[t][13] + " if atk";
                                            } else {
                                                labeling[t] += skills[t][8] + " Rune, " + skills[t][13] + " if atk";
                                            }
                                        }
                                        break;
                                    case "only if atk":
                                        if (!"Rune".equals(skills[t][7])) {
                                            if (!"0".equals(skills[t][4])) {
                                                labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                            }
                                            labeling[t] += skills[t][7] + " " + skills[t][8] + " if atk";
                                        } else {
                                            if (!"0".equals(skills[t][4])) {
                                                labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                            }
                                            if (Integer.parseInt(skills[t][8]) > 1) {
                                                labeling[t] += skills[t][8] + " Runes if atk";
                                            } else {
                                                labeling[t] += skills[t][8] + " Rune if atk";
                                            }
                                        }
                                        break;
                                }
                                break;
                            case "Defender":
                                switch (skills[t][14]) {
                                    case "[VAL] if dfd":
                                        if (!"Rune".equals(skills[t][7])) {
                                            if (!"0".equals(skills[t][4])) {
                                                labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                            }
                                            labeling[t] += skills[t][7] + " " + skills[t][8] + ", " + skills[t][13] + " if dfd";
                                        } else {
                                            if (!"0".equals(skills[t][4])) {
                                                labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                            }
                                            if (Integer.parseInt(skills[t][8]) > 1) {
                                                labeling[t] += skills[t][8] + " Runes, " + skills[t][13] + " if dfd";
                                            } else {
                                                labeling[t] += skills[t][8] + " Rune, " + skills[t][13] + " if atk";
                                            }
                                        }
                                        break;
                                    case "only if dfd":
                                        if (!"Rune".equals(skills[t][7])) {
                                            if (!"0".equals(skills[t][4])) {
                                                labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                            }
                                            labeling[t] += skills[t][7] + " " + skills[t][8] + " if dfd";
                                        } else {
                                            if (!"0".equals(skills[t][4])) {
                                                labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                            }
                                            if (Integer.parseInt(skills[t][8]) > 1) {
                                                labeling[t] += skills[t][8] + " Runes if dfd";
                                            } else {
                                                labeling[t] += skills[t][8] + " Rune if dfd";
                                            }
                                        }
                                        break;
                                }
                                break;
                            case "Bonus/Malus":
                                switch (skills[t][14]) {
                                    case "[VAL] x":
                                        if (!"0".equals(skills[t][4])) {
                                            labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                        }
                                        if (!"t".equals(skills[t][1]) && !"t".equals(skills[t][2]) && (skills[t][7].equals(skills[t][15]) || ("Strength +".equals(skills[t][7]) && "Strength".equals(skills[t][15])))) {
                                            if ("Strength +".equals(skills[t][7])) {
                                                labeling[t] += "Strength x" + (Integer.parseInt(skills[t][8]) + 1);
                                            } else {
                                                labeling[t] += skills[t][7] + " x" + (Integer.parseInt(skills[t][8]) + 1);
                                            }
                                        } else if (!"1".equals(skills[t][8])) {
                                            labeling[t] += skills[t][7] + " = " + skills[t][8] + " x " + skills[t][15];
                                        } else {
                                            labeling[t] += skills[t][7] + " = " + skills[t][15];
                                        }
                                        break;
                                    case "[VAL] +":
                                        if (!"0".equals(skills[t][4])) {
                                            labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                        }
                                        if (!"0".equals(skills[t][8])) {
                                            labeling[t] += skills[t][7] + " = " + skills[t][15] + " + " + skills[t][8];
                                        } else {
                                            labeling[t] += skills[t][7] + " = " + skills[t][15];
                                        }
                                        break;
                                    case "[VAL] -":
                                        if (!"0".equals(skills[t][4])) {
                                            labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                        }
                                        labeling[t] += skills[t][7] + " = " + skills[t][8] + " - " + skills[t][15];
                                        break;
                                    case "/ [VAL]":
                                        if (!"0".equals(skills[t][4])) {
                                            labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                        }
                                        if (!"1".equals(skills[t][8])) {
                                            labeling[t] += skills[t][7] + " = " + skills[t][15] + " / " + skills[t][8];
                                        } else {
                                            labeling[t] += skills[t][7] + " = " + skills[t][15];
                                        }
                                        break;
                                    case "+[VAL] per":
                                        if (!"0".equals(skills[t][4])) {
                                            labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                        }
                                        if (!"1".equals(skills[t][13])) {
                                            labeling[t] += skills[t][7] + " = " + skills[t][8] + " +" + skills[t][13] + " x " + skills[t][15];
                                        } else {
                                            labeling[t] += skills[t][7] + " = " + skills[t][8] + " + " + skills[t][15];
                                        }
                                        break;
                                    case "-[VAL] per":
                                        if (!"0".equals(skills[t][4])) {
                                            labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                        }
                                        if (!"1".equals(skills[t][13])) {
                                            labeling[t] += skills[t][7] + " = " + skills[t][8] + " -" + skills[t][13] + " x " + skills[t][15];
                                        } else {
                                            labeling[t] += skills[t][7] + " = " + skills[t][8] + " - " + skills[t][15];
                                        }
                                        break;
                                }
                                break;
                            case "opp Bonus/Malus":
                                switch (skills[t][14]) {
                                    case "[VAL] x":
                                        if (!"0".equals(skills[t][4])) {
                                            labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                        }
                                        if (!"t".equals(skills[t][1]) && "t".equals(skills[t][2]) && (skills[t][7].equals(skills[t][15]) || ("Strength +".equals(skills[t][7]) && "Strength".equals(skills[t][15])))) {
                                            if ("Strength +".equals(skills[t][7])) {
                                                labeling[t] += "Strength x" + (Integer.parseInt(skills[t][8]) + 1);
                                            } else {
                                                labeling[t] += skills[t][7] + " x" + (Integer.parseInt(skills[t][8]) + 1);
                                            }
                                        } else if (!"1".equals(skills[t][8])) {
                                            labeling[t] += skills[t][7] + " = " + skills[t][8] + " x opp " + skills[t][15];
                                        } else {
                                            labeling[t] += skills[t][7] + " = opp " + skills[t][15];
                                        }
                                        break;
                                    case "[VAL] +":
                                        if (!"0".equals(skills[t][4])) {
                                            labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                        }
                                        if (!"0".equals(skills[t][8])) {
                                            labeling[t] += skills[t][7] + " = opp " + skills[t][15] + " + " + skills[t][8];
                                        } else {
                                            labeling[t] += skills[t][7] + " = opp " + skills[t][15];
                                        }
                                        break;
                                    case "[VAL] -":
                                        if (!"0".equals(skills[t][4])) {
                                            labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                        }
                                        labeling[t] += skills[t][7] + " = " + skills[t][8] + " - opp " + skills[t][15];
                                        break;
                                    case "/ [VAL]":
                                        if (!"0".equals(skills[t][4])) {
                                            labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                        }
                                        if (!"1".equals(skills[t][8])) {
                                            labeling[t] += skills[t][7] + " = opp " + skills[t][15] + " / " + skills[t][8];
                                        } else {
                                            labeling[t] += skills[t][7] + " = opp " + skills[t][15];
                                        }
                                        break;
                                    case "+[VAL] per":
                                        if (!"0".equals(skills[t][4])) {
                                            labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                        }
                                        if (!"1".equals(skills[t][13])) {
                                            labeling[t] += skills[t][7] + " = " + skills[t][8] + " +" + skills[t][13] + " x opp " + skills[t][15];
                                        } else {
                                            labeling[t] += skills[t][7] + " = " + skills[t][8] + " + opp " + skills[t][15];
                                        }
                                        break;
                                    case "-[VAL] per":
                                        if (!"0".equals(skills[t][4])) {
                                            labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                        }
                                        if (!"1".equals(skills[t][13])) {
                                            labeling[t] += skills[t][7] + " = " + skills[t][8] + " -" + skills[t][13] + " x opp " + skills[t][15];
                                        } else {
                                            labeling[t] += skills[t][7] + " = " + skills[t][8] + " - opp " + skills[t][15];
                                        }
                                        break;
                                }
                                break;
                            case "if":
                                labeling[t] += skills[t][7] + " " + skills[t][8] + ", " + skills[t][13] + " if " + skills[t][15];
                                break;
                        }
                        break;
                    case "damaging":

                        if ("r".equals(skills[t][6]) || "b".equals(skills[t][6]) || "y".equals(skills[t][6]) || "s".equals(skills[t][6])) {
                            skills[t][6].toUpperCase();
                        }
                        switch (skills[t][12]) {
                            case "No Add Effect":
                                if (!"1".equals(skills[t][6])) {
                                    labeling[t] += skills[t][6] + "x ";
                                }
                                if (!"0".equals(skills[t][4])) {
                                    labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                }
                                labeling[t] += skills[t][7] + " " + skills[t][8];
                                break;
                            case "Dice":
                                switch (skills[t][14]) {
                                    case "[VAL] x":
                                        if (!"1".equals(skills[t][6])) {
                                            labeling[t] += skills[t][6] + "x ";
                                        }
                                        if (!"0".equals(skills[t][4])) {
                                            labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                        }
                                        labeling[t] += skills[t][7] + " " + skills[t][8] + " x " + skills[t][15].substring(0, 1);
                                        break;
                                    case "+[VAL] per":
                                        if (!"1".equals(skills[t][6])) {
                                            labeling[t] += skills[t][6] + "x ";
                                        }
                                        if (!"0".equals(skills[t][4])) {
                                            labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                        }
                                        labeling[t] += skills[t][7] + " " + skills[t][8] + " +" + skills[t][13] + " x " + skills[t][15].substring(0, 1);
                                        break;
                                    case "-[VAL] per":
                                        if (!"1".equals(skills[t][6])) {
                                            labeling[t] += skills[t][6] + "x ";
                                        }
                                        if (!"0".equals(skills[t][4])) {
                                            labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                        }
                                        labeling[t] += skills[t][7] + " " + skills[t][8] + " -" + skills[t][13] + " x " + skills[t][15].substring(0, 1);
                                        break;
                                }
                                break;
                            case "Opp Dice":
                                switch (skills[t][14]) {
                                    case "[VAL] x":
                                        if (!"1".equals(skills[t][6])) {
                                            labeling[t] += skills[t][6] + "x ";
                                        }
                                        if (!"0".equals(skills[t][4])) {
                                            labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                        }
                                        labeling[t] += skills[t][7] + " " + skills[t][8] + " x opp " + skills[t][15].substring(0, 1);
                                        break;
                                    case "+[VAL] per":
                                        if (!"1".equals(skills[t][6])) {
                                            labeling[t] += skills[t][6] + "x ";
                                        }
                                        if (!"0".equals(skills[t][4])) {
                                            labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                        }
                                        labeling[t] += skills[t][7] + " " + skills[t][8] + " +" + skills[t][13] + " x opp " + skills[t][15].substring(0, 1);
                                        break;
                                    case "-[VAL] per":
                                        if (!"1".equals(skills[t][6])) {
                                            labeling[t] += skills[t][6] + "x ";
                                        }
                                        if (!"0".equals(skills[t][4])) {
                                            labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                        }
                                        labeling[t] += skills[t][7] + " " + skills[t][8] + " -" + skills[t][13] + " x opp " + skills[t][15].substring(0, 1);
                                        break;
                                }
                                break;
                            case "VS":
                                switch (skills[t][14]) {
                                    case "[VAL] VS":
                                        if (!"1".equals(skills[t][6])) {
                                            labeling[t] += skills[t][6] + "x ";
                                        }
                                        if (!"0".equals(skills[t][4])) {
                                            labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                        }
                                        labeling[t] += skills[t][7] + " " + skills[t][8] + ", " + skills[t][13] + " VS " + skills[t][15];
                                        break;
                                    case "only VS":
                                        if (!"1".equals(skills[t][6])) {
                                            labeling[t] += skills[t][6] + "x ";
                                        }
                                        if (!"0".equals(skills[t][4])) {
                                            labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                        }
                                        labeling[t] += skills[t][7] + " " + skills[t][8] + " VS " + skills[t][15];
                                        break;
                                }
                                break;
                            case "Ally":
                                switch (skills[t][14]) {
                                    case "[VAL] x":
                                        if (!"1".equals(skills[t][6])) {
                                            labeling[t] += skills[t][6] + "x ";
                                        }
                                        if (!"0".equals(skills[t][4])) {
                                            labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                        }
                                        if (!"Ally".equals(skills[t][15])) {
                                            labeling[t] += skills[t][7] + " " + skills[t][8] + " x " + skills[t][15] + " ally";
                                        } else {
                                            labeling[t] += skills[t][7] + " " + skills[t][8] + " x " + skills[t][15];
                                        }
                                        break;
                                    case "+[VAL] per":
                                        if (!"1".equals(skills[t][6])) {
                                            labeling[t] += skills[t][6] + "x ";
                                        }
                                        if (!"0".equals(skills[t][4])) {
                                            labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                        }
                                        if (!"Ally".equals(skills[t][15])) {
                                            labeling[t] += skills[t][7] + " " + skills[t][8] + " +" + skills[t][13] + " x " + skills[t][15] + " ally";
                                        } else {
                                            labeling[t] += skills[t][7] + " " + skills[t][8] + " +" + skills[t][13] + " x " + skills[t][15];
                                        }
                                        break;
                                    case "-[VAL] per":
                                        if (!"1".equals(skills[t][6])) {
                                            labeling[t] += skills[t][6] + "x ";
                                        }
                                        if (!"0".equals(skills[t][4])) {
                                            labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                        }
                                        if (!"Ally".equals(skills[t][15])) {
                                            labeling[t] += skills[t][7] + " " + skills[t][8] + " -" + skills[t][13] + " x " + skills[t][15] + " ally";
                                        } else {
                                            labeling[t] += skills[t][7] + " " + skills[t][8] + " -" + skills[t][13] + " x " + skills[t][15];
                                        }
                                        break;
                                }
                                break;
                            case "Opp":
                                switch (skills[t][14]) {
                                    case "[VAL] x":
                                        if (!"1".equals(skills[t][6])) {
                                            labeling[t] += skills[t][6] + "x ";
                                        }
                                        if (!"0".equals(skills[t][4])) {
                                            labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                        }
                                        if (!"Ally".equals(skills[t][15])) {
                                            labeling[t] += skills[t][7] + " " + skills[t][8] + " x " + skills[t][15] + " opp";
                                        } else {
                                            labeling[t] += skills[t][7] + " " + skills[t][8] + " x " + skills[t][15];
                                        }
                                        break;
                                    case "+[VAL] per":
                                        if (!"1".equals(skills[t][6])) {
                                            labeling[t] += skills[t][6] + "x ";
                                        }
                                        if (!"0".equals(skills[t][4])) {
                                            labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                        }
                                        if (!"Ally".equals(skills[t][15])) {
                                            labeling[t] += skills[t][7] + " " + skills[t][8] + " +" + skills[t][13] + " x " + skills[t][15] + " opp";
                                        } else {
                                            labeling[t] += skills[t][7] + " " + skills[t][8] + " +" + skills[t][13] + " x " + skills[t][15];
                                        }
                                        break;
                                    case "-[VAL] per":
                                        if (!"1".equals(skills[t][6])) {
                                            labeling[t] += skills[t][6] + "x ";
                                        }
                                        if (!"0".equals(skills[t][4])) {
                                            labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                        }
                                        if (!"Ally".equals(skills[t][15])) {
                                            labeling[t] += skills[t][7] + " " + skills[t][8] + " -" + skills[t][13] + " x " + skills[t][15] + " opp";
                                        } else {
                                            labeling[t] += skills[t][7] + " " + skills[t][8] + " -" + skills[t][13] + " x " + skills[t][15];
                                        }
                                        break;
                                }
                                break;
                            case "Attacker":
                                switch (skills[t][14]) {
                                    case "[VAL] if atk":
                                        if (!"1".equals(skills[t][6])) {
                                            labeling[t] += skills[t][6] + "x ";
                                        }
                                        if (!"0".equals(skills[t][4])) {
                                            labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                        }
                                        labeling[t] += skills[t][7] + " " + skills[t][8] + ", " + skills[t][13] + " if atk";
                                        break;
                                    case "only if atk":
                                        if (!"1".equals(skills[t][6])) {
                                            labeling[t] += skills[t][6] + "x ";
                                        }
                                        if (!"0".equals(skills[t][4])) {
                                            labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                        }
                                        labeling[t] += skills[t][7] + " " + skills[t][8] + " if atk";
                                        break;
                                }
                                break;
                            case "Defender":
                                switch (skills[t][14]) {
                                    case "[VAL] if dfd":
                                        if (!"1".equals(skills[t][6])) {
                                            labeling[t] += skills[t][6] + "x ";
                                        }
                                        if (!"0".equals(skills[t][4])) {
                                            labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                        }
                                        labeling[t] += skills[t][7] + " " + skills[t][8] + ", " + skills[t][13] + " if dfd";
                                        break;
                                    case "only if dfd":
                                        if (!"1".equals(skills[t][6])) {
                                            labeling[t] += skills[t][6] + "x ";
                                        }
                                        if (!"0".equals(skills[t][4])) {
                                            labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                        }
                                        labeling[t] += skills[t][7] + " " + skills[t][8] + " if dfd";
                                        break;
                                }
                                break;
                            case "Bonus/Malus":
                                switch (skills[t][14]) {
                                    case "[VAL] x":
                                        if (!"1".equals(skills[t][6])) {
                                            labeling[t] += skills[t][6] + "x ";
                                        }
                                        if (!"0".equals(skills[t][4])) {
                                            labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                        }
                                        if (!"1".equals(skills[t][8])) {
                                            labeling[t] += skills[t][7] + " = " + skills[t][8] + " x " + skills[t][15];
                                        } else {
                                            labeling[t] += skills[t][7] + " = " + skills[t][15];
                                        }
                                        break;
                                    case "[VAL] +":
                                        if (!"1".equals(skills[t][6])) {
                                            labeling[t] += skills[t][6] + "x ";
                                        }
                                        if (!"0".equals(skills[t][4])) {
                                            labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                        }
                                        if (!"0".equals(skills[t][8])) {
                                            labeling[t] += skills[t][7] + " = " + skills[t][15] + " + " + skills[t][8];
                                        } else {
                                            labeling[t] += skills[t][7] + " = " + skills[t][15];
                                        }
                                        break;
                                    case "[VAL] -":
                                        if (!"1".equals(skills[t][6])) {
                                            labeling[t] += skills[t][6] + "x ";
                                        }
                                        if (!"0".equals(skills[t][4])) {
                                            labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                        }
                                        labeling[t] += skills[t][7] + " = " + skills[t][8] + " - " + skills[t][15];
                                        break;
                                    case "/ [VAL]":
                                        if (!"1".equals(skills[t][6])) {
                                            labeling[t] += skills[t][6] + "x ";
                                        }
                                        if (!"0".equals(skills[t][4])) {
                                            labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                        }
                                        if (!"1".equals(skills[t][8])) {
                                            labeling[t] += skills[t][7] + " = " + skills[t][15] + " / " + skills[t][8];
                                        } else {
                                            labeling[t] += skills[t][7] + " = " + skills[t][15];
                                        }
                                        break;
                                    case "+[VAL] per":
                                        if (!"1".equals(skills[t][6])) {
                                            labeling[t] += skills[t][6] + "x ";
                                        }
                                        if (!"0".equals(skills[t][4])) {
                                            labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                        }
                                        if (!"1".equals(skills[t][13])) {
                                            labeling[t] += skills[t][7] + " = " + skills[t][8] + " +" + skills[t][13] + " x " + skills[t][15];
                                        } else {
                                            labeling[t] += skills[t][7] + " = " + skills[t][8] + " + " + skills[t][15];
                                        }
                                        break;
                                    case "-[VAL] per":
                                        if (!"1".equals(skills[t][6])) {
                                            labeling[t] += skills[t][6] + "x ";
                                        }
                                        if (!"0".equals(skills[t][4])) {
                                            labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                        }
                                        if (!"1".equals(skills[t][13])) {
                                            labeling[t] += skills[t][7] + " = " + skills[t][8] + " -" + skills[t][13] + " x " + skills[t][15];
                                        } else {
                                            labeling[t] += skills[t][7] + " = " + skills[t][8] + " - " + skills[t][15];
                                        }
                                        break;
                                }
                                break;
                            case "opp Bonus/Malus":
                                switch (skills[t][14]) {
                                    case "[VAL] x":
                                        if (!"1".equals(skills[t][6])) {
                                            labeling[t] += skills[t][6] + "x ";
                                        }
                                        if (!"0".equals(skills[t][4])) {
                                            labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                        }
                                        if (!"1".equals(skills[t][8])) {
                                            labeling[t] += skills[t][7] + " = " + skills[t][8] + " x opp " + skills[t][15];
                                        } else {
                                            labeling[t] += skills[t][7] + " = opp " + skills[t][15];
                                        }
                                        break;
                                    case "[VAL] +":
                                        if (!"1".equals(skills[t][6])) {
                                            labeling[t] += skills[t][6] + "x ";
                                        }
                                        if (!"0".equals(skills[t][4])) {
                                            labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                        }
                                        if (!"0".equals(skills[t][8])) {
                                            labeling[t] += skills[t][7] + " = opp " + skills[t][15] + " + " + skills[t][8];
                                        } else {
                                            labeling[t] += skills[t][7] + " = opp " + skills[t][15];
                                        }
                                        break;
                                    case "[VAL] -":
                                        if (!"1".equals(skills[t][6])) {
                                            labeling[t] += skills[t][6] + "x ";
                                        }
                                        if (!"0".equals(skills[t][4])) {
                                            labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                        }
                                        labeling[t] += skills[t][7] + " = " + skills[t][8] + " - opp " + skills[t][15];
                                        break;
                                    case "/ [VAL]":
                                        if (!"1".equals(skills[t][6])) {
                                            labeling[t] += skills[t][6] + "x ";
                                        }
                                        if (!"0".equals(skills[t][4])) {
                                            labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                        }
                                        if (!"1".equals(skills[t][8])) {
                                            labeling[t] += skills[t][7] + " = opp " + skills[t][15] + " / " + skills[t][8];
                                        } else {
                                            labeling[t] += skills[t][7] + " = opp " + skills[t][15];
                                        }
                                        break;
                                    case "+[VAL] per":
                                        if (!"1".equals(skills[t][6])) {
                                            labeling[t] += skills[t][6] + "x ";
                                        }
                                        if (!"0".equals(skills[t][4])) {
                                            labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                        }
                                        if (!"1".equals(skills[t][13])) {
                                            labeling[t] += skills[t][7] + " = " + skills[t][8] + " +" + skills[t][13] + " x opp " + skills[t][15];
                                        } else {
                                            labeling[t] += skills[t][7] + " = " + skills[t][8] + " + opp " + skills[t][15];
                                        }
                                        break;
                                    case "-[VAL] per":
                                        if (!"1".equals(skills[t][6])) {
                                            labeling[t] += skills[t][6] + "x ";
                                        }
                                        if (!"0".equals(skills[t][4])) {
                                            labeling[t] += cbbType.getItemAt(Integer.parseInt(skills[t][4])) + " ";
                                        }
                                        if (!"1".equals(skills[t][13])) {
                                            labeling[t] += skills[t][7] + " = " + skills[t][8] + " -" + skills[t][13] + " x opp " + skills[t][15];
                                        } else {
                                            labeling[t] += skills[t][7] + " = " + skills[t][8] + " - opp " + skills[t][15];
                                        }
                                        break;
                                }
                                break;

                        }
                        break;
                    case "DejaPAing":
                        switch (skills[t][12]) {
                            case "No Add Effect":
                                if (!"1".equals(skills[t][6]) || "Physical Attack".equals(skills[t][7])) {
                                    labeling[t] += skills[t][6] + "x ";
                                }
                                labeling[t] += skills[t][7];
                                break;
                            case "Dice":
                                switch (skills[t][14]) {
                                    case "[VAL] x":
                                        labeling[t] += skills[t][6] + "x " + skills[t][7] + " x " + skills[t][15].substring(0, 1);
                                        break;
                                    case "+[VAL] per":
                                        labeling[t] += skills[t][6] + "x " + skills[t][7] + " +" + skills[t][13] + " x " + skills[t][15].substring(0, 1);
                                        break;
                                    case "-[VAL] per":
                                        labeling[t] += skills[t][6] + "x " + skills[t][7] + " -" + skills[t][13] + " x " + skills[t][15].substring(0, 1);
                                        break;
                                }
                                break;
                            case "Opp Dice":
                                switch (skills[t][14]) {
                                    case "[VAL] x":
                                        labeling[t] += skills[t][6] + "x " + skills[t][7] + " x opp " + skills[t][15].substring(0, 1);
                                        break;
                                    case "+[VAL] per":
                                        labeling[t] += skills[t][6] + "x " + skills[t][7] + " +" + skills[t][13] + " x opp " + skills[t][15].substring(0, 1);
                                        break;
                                    case "-[VAL] per":
                                        labeling[t] += skills[t][6] + "x " + skills[t][7] + " -" + skills[t][13] + " x opp " + skills[t][15].substring(0, 1);
                                        break;
                                }
                                break;
                            case "VS":
                                switch (skills[t][14]) {
                                    case "[VAL] VS":
                                        labeling[t] += skills[t][6] + "x " + skills[t][7] + ", " + skills[t][13] + "x VS " + skills[t][15];
                                        break;
                                    case "only VS":
                                        if (!"1".equals(skills[t][6])) {
                                            labeling[t] += skills[t][6] + "x ";
                                        }
                                        labeling[t] += skills[t][7] + " VS " + skills[t][15];
                                        break;
                                }
                                break;
                            case "Ally":
                                switch (skills[t][14]) {
                                    case "[VAL] x":
                                        labeling[t] += skills[t][6] + "x ";
                                        if (!"Ally".equals(skills[t][15])) {
                                            labeling[t] += skills[t][7] + " x " + skills[t][15] + " ally";
                                        } else {
                                            labeling[t] += skills[t][7] + " x " + skills[t][15];
                                        }
                                        break;
                                    case "+[VAL] per":
                                        labeling[t] += skills[t][6] + "x ";
                                        if (!"Ally".equals(skills[t][15])) {
                                            labeling[t] += skills[t][7] + " +" + skills[t][13] + " x " + skills[t][15] + " ally";
                                        } else {
                                            labeling[t] += skills[t][7] + " +" + skills[t][13] + " x " + skills[t][15];
                                        }
                                        break;
                                    case "-[VAL] per":
                                        labeling[t] += skills[t][6] + "x ";
                                        if (!"Ally".equals(skills[t][15])) {
                                            labeling[t] += skills[t][7] + " -" + skills[t][13] + " x " + skills[t][15] + " ally";
                                        } else {
                                            labeling[t] += skills[t][7] + " -" + skills[t][13] + " x " + skills[t][15];
                                        }
                                        break;
                                }
                                break;
                            case "Opp":
                                switch (skills[t][14]) {
                                    case "[VAL] x":
                                        labeling[t] += skills[t][6] + "x ";
                                        if (!"Ally".equals(skills[t][15])) {
                                            labeling[t] += skills[t][7] + " x " + skills[t][15] + " opp";
                                        } else {
                                            labeling[t] += skills[t][7] + " x " + skills[t][15];
                                        }
                                        break;
                                    case "+[VAL] per":
                                        labeling[t] += skills[t][6] + "x ";
                                        if (!"Ally".equals(skills[t][15])) {
                                            labeling[t] += skills[t][7] + " +" + skills[t][13] + " x " + skills[t][15] + " opp";
                                        } else {
                                            labeling[t] += skills[t][7] + " +" + skills[t][13] + " x " + skills[t][15];
                                        }
                                        break;
                                    case "-[VAL] per":
                                        labeling[t] += skills[t][6] + "x ";
                                        if (!"Ally".equals(skills[t][15])) {
                                            labeling[t] += skills[t][7] + " -" + skills[t][13] + " x " + skills[t][15] + " opp";
                                        } else {
                                            labeling[t] += skills[t][7] + " -" + skills[t][13] + " x " + skills[t][15];
                                        }
                                        break;
                                }
                                break;
                            case "Attacker":
                                switch (skills[t][14]) {
                                    case "[VAL] if atk":
                                        labeling[t] += skills[t][6] + "x " + skills[t][7] + ", " + skills[t][13] + "x if atk";
                                        ;
                                        break;
                                    case "only if atk":
                                        if (!"1".equals(skills[t][6])) {
                                            labeling[t] += skills[t][6] + "x ";
                                        }
                                        labeling[t] += skills[t][7] + " if atk";
                                        break;
                                }
                                break;
                            case "Defender":
                                switch (skills[t][14]) {
                                    case "[VAL] if dfd":
                                        labeling[t] += skills[t][6] + "x " + skills[t][7] + ", " + skills[t][13] + "x if dfd";
                                        break;
                                    case "only if dfd":
                                        if (!"1".equals(skills[t][6])) {
                                            labeling[t] += skills[t][6] + "x ";
                                        }
                                        labeling[t] += skills[t][7] + " if dfd";
                                        break;
                                }
                                break;
                            case "Bonus/Malus":
                                switch (skills[t][14]) {
                                    case "[VAL] x":
                                        labeling[t] += skills[t][6] + "x " + skills[t][7] + " x " + skills[t][15];
                                        break;
                                    case "+[VAL] per":
                                        labeling[t] += skills[t][6] + "x " + skills[t][7] + " +" + skills[t][13] + " x " + skills[t][15];
                                        break;
                                    case "-[VAL] per":
                                        labeling[t] += skills[t][6] + "x " + skills[t][7] + " -" + skills[t][13] + " x " + skills[t][15];
                                        break;
                                }
                                break;
                            case "opp Bonus/Malus":
                                switch (skills[t][14]) {
                                    case "[VAL] x":
                                        labeling[t] += skills[t][6] + "x " + skills[t][7] + " x opp " + skills[t][15];
                                        break;
                                    case "+[VAL] per":
                                        labeling[t] += skills[t][6] + "x " + skills[t][7] + " +" + skills[t][13] + " x opp " + skills[t][15];
                                        break;
                                    case "-[VAL] per":
                                        labeling[t] += skills[t][6] + "x " + skills[t][7] + " -" + skills[t][13] + " x opp " + skills[t][15];
                                        break;
                                }
                                break;

                        }
                        break;
                    case "inspiring":
                        labeling[t] += "Inspire " + cbbAny.getItemAt(Integer.parseInt(skills[t][3]));
                        break;
                    case "symbing":
                        labeling[t] += "Symbiosis " + cbbAny.getItemAt(Integer.parseInt(skills[t][3])) + " Dragon";
                        break;
                    case "dicing":
                        String[] dadoes = {"R", "B", "Y", "S"};
                        if ("t".equals(skills[t][2])) {
                            labeling[t] += "opp ";
                        }
                        if (!"1".equals(skills[t][6])) {
                            if (Integer.parseInt(skills[t][6]) < diceLimit) {
                                labeling[t] += skills[t][6];
                            } else {
                                labeling[t] += "All ";
                            }
                        }

                        labeling[t] += dadoes[Integer.parseInt(skills[t][9])] + " > " + dadoes[Integer.parseInt(skills[t][10])];

                        if ("only VS".equals(skills[t][14])) {
                            labeling[t] += " VS " + skills[t][15];
                        }
                        if ("only if atk".equals(skills[t][14])) {
                            labeling[t] += " if atk";
                        }
                        if ("only if dfd".equals(skills[t][14])) {
                            labeling[t] += " if dfd";
                        }
                        break;
                }

                if (!"0".equals(skills[t][17])) {
                    switch (cbbAnotherAddDMG.getItemAt(Integer.parseInt(skills[t][17]))) {
                        case "[VAL] if atk":
                            labeling[t] += ", " + skills[t][18] + " if atk";
                            break;
                        case "[VAL] if dfd":
                            labeling[t] += ", " + skills[t][18] + " if dfd";
                            break;
                        case "[VAL] VS":
                            labeling[t] += ", " + skills[t][18] + " VS " + cbbAnotherOperation.getItemAt(Integer.parseInt(skills[t][19]));
                            break;
                        case "another VS":
                            labeling[t] += " or " + cbbAnotherOperation.getItemAt(Integer.parseInt(skills[t][19]));
                            break;
                    }
                }
            }
        }
        lblB1.setText(labeling[0]);
        lblB2.setText(labeling[1]);
        lblB3.setText(labeling[2]);
        lblM1.setText(labeling[3]);
        lblM2.setText(labeling[4]);
        lblM3.setText(labeling[5]);
        lblT1.setText(labeling[6]);
        lblT2.setText(labeling[7]);
        lblT3.setText(labeling[8]);

        updateSidePs();

    }

    private void updateSidePs() {
        String first = labeling[0];
        if (skills[1][0].equals("yes")) {
            if (!labeling[0].contains("All ")) {
                first += " & " + labeling[1];
            } else {
                first += ", " + labeling[1].replace("All ", "");
            }
        }
        if (skills[2][0].equals("yes")) {
            if (!labeling[0].contains("All ") && !labeling[1].contains("All ")) {
                first += ", " + labeling[2];
            } else {
                first += ", " + labeling[2].replace("All ", "");
            }
        }
        String second = labeling[3];
        if (skills[4][0].equals("yes")) {
            if (!labeling[3].contains("All ")) {
                second += " & " + labeling[4];
            } else {
                second += ", " + labeling[4].replace("All ", "");
            }
        }
        if (skills[5][0].equals("yes")) {
            if (!labeling[3].contains("All ") && !labeling[4].contains("All ")) {
                second += ", " + labeling[5];
            } else {
                second += ", " + labeling[5].replace("All ", "");
            }
        }
        String third = labeling[6];
        if (skills[7][0].equals("yes")) {
            if (!labeling[6].contains("All ")) {
                third += " & " + labeling[7];
            } else {
                third += ", " + labeling[7].replace("All ", "");
            }
        }
        if (skills[8][0].equals("yes")) {
            if (!labeling[6].contains("All ") && !labeling[7].contains("All ")) {
                third += ", " + labeling[8];
            } else {
                third += ", " + labeling[8].replace("All ", "");
            }
        }

        lblAbyBOT.setText(first);
        lblAbyMID.setText(second);
        lblAbyTOP.setText(third);

        JLabel[] botabi = {lblDBOT1, lblDBOT2, lblDBOT3, lblDBOT4, lblDBOT5};
        JLabel[] midabi = {lblDMID1, lblDMID2, lblDMID3, lblDMID4, lblDMID5};
        JLabel[] topabi = {lblDTOP1, lblDTOP2, lblDTOP3, lblDTOP4, lblDTOP5};
        String[] abibot = {"Red", "Blue", "Yellow", "Sword"};
        int[] nlbls1 = {0, 0, 0, 0, 0};
        int[] nlbls2 = {0, 0, 0, 0, 0};
        int[] nlbls3 = {0, 0, 0, 0, 0};

        int currentb = 0;
        int currentm = 0;
        int currentt = 0;

        nlbls1[0] = BD1.getSelectedIndex();
        nlbls1[1] = BD2.getSelectedIndex();
        nlbls1[2] = BD3.getSelectedIndex();
        nlbls1[3] = BD4.getSelectedIndex();
        nlbls1[4] = BD5.getSelectedIndex();
        nlbls2[0] = MD1.getSelectedIndex();
        nlbls2[1] = MD2.getSelectedIndex();
        nlbls2[2] = MD3.getSelectedIndex();
        nlbls2[3] = MD4.getSelectedIndex();
        nlbls2[4] = MD5.getSelectedIndex();
        nlbls3[0] = TD1.getSelectedIndex();
        nlbls3[1] = TD2.getSelectedIndex();
        nlbls3[2] = TD3.getSelectedIndex();
        nlbls3[3] = TD4.getSelectedIndex();
        nlbls3[4] = TD5.getSelectedIndex();

        for (int counterLabels = 0; counterLabels < 5; counterLabels++) {

            botabi[counterLabels].setIcon(null);
            midabi[counterLabels].setIcon(null);
            topabi[counterLabels].setIcon(null);

            if (nlbls1[counterLabels] != 0) {
                botabi[currentb].setIcon(createImageIcon("/IMG/" + abibot[nlbls1[counterLabels] - 1] + ".png"));
                currentb++;
            }
            if (nlbls2[counterLabels] != 0) {
                midabi[currentm].setIcon(createImageIcon("/IMG/" + abibot[nlbls2[counterLabels] - 1] + ".png"));
                currentm++;
            }
            if (nlbls3[counterLabels] != 0) {
                topabi[currentt].setIcon(createImageIcon("/IMG/" + abibot[nlbls3[counterLabels] - 1] + ".png"));
                currentt++;
            }
        }

    }

    private void mudandoOperation(String mandai) {
        switch (mandai) {
            case "No Add Effect":
                cbbOperation.setVisible(false);
                cbbChange.setVisible(false);
                txtAddDMG.setVisible(false);
                lblCounter.setVisible(false);
                counter.setVisible(false);
                lblAnotherWall.setVisible(false);
                lblAddAnother.setVisible(false);
                cbbAnotherAddDMG.setVisible(false);
                cbbAnotherAddDMG.setSelectedIndex(0);
                txtAnotherAddDMG.setVisible(false);
                cbbAnotherOperation.setVisible(false);
                txtShield.setVisible(false);
                break;
            case "Dice":
                cbbOperation.setVisible(true);
                cbbChange.setVisible(true);
                cbbOperation.setModel(new DefaultComboBoxModel<>(new String[]{"[VAL] x", "+[VAL] per", "-[VAL] per"}));
                cbbChange.setModel(new DefaultComboBoxModel<>(new String[]{"Red", "Blue", "Yellow", "Sword"}));
                txtAddDMG.setVisible(false);
                lblCounter.setVisible(false);
                counter.setVisible(false);
                txtShield.setVisible(false);

                lblAnotherWall.setVisible(true);
                lblAddAnother.setVisible(true);
                cbbAnotherAddDMG.setVisible(true);
                cbbAnotherAddDMG.setModel(new DefaultComboBoxModel<>(new String[]{"No Add Effect", "[VAL] VS", "[VAL] if atk", "[VAL] if dfd"}));
                if (cbbAnotherAddDMG.getSelectedIndex() != 0) {
                    txtAnotherAddDMG.setVisible(true);
                    cbbAnotherOperation.setVisible(true);
                } else {
                    txtAnotherAddDMG.setVisible(false);
                    cbbAnotherOperation.setVisible(false);
                }
                break;
            case "Opp Dice":
                cbbOperation.setVisible(true);
                txtShield.setVisible(false);
                cbbChange.setVisible(true);
                cbbOperation.setModel(new DefaultComboBoxModel<>(new String[]{"[VAL] x", "+[VAL] per", "-[VAL] per"}));
                cbbChange.setModel(new DefaultComboBoxModel<>(new String[]{"Red", "Blue", "Yellow", "Sword"}));
                txtAddDMG.setVisible(false);
                lblCounter.setVisible(true);
                lblCounter.setText("How many opp " + cbbChange.getSelectedItem() + " dices?");
                counter.setVisible(true);
                counter.setModel(new javax.swing.SpinnerNumberModel((int) counter.getValue(), 0, 7, 1));

                lblAnotherWall.setVisible(true);
                lblAddAnother.setVisible(true);
                txtShield.setVisible(false);
                cbbAnotherAddDMG.setVisible(true);
                cbbAnotherAddDMG.setModel(new DefaultComboBoxModel<>(new String[]{"No Add Effect", "[VAL] VS", "[VAL] if atk", "[VAL] if dfd"}));
                if (cbbAnotherAddDMG.getSelectedIndex() != 0) {
                    txtAnotherAddDMG.setVisible(true);
                    cbbAnotherOperation.setVisible(true);
                } else {
                    txtAnotherAddDMG.setVisible(false);
                    cbbAnotherOperation.setVisible(false);
                }
                break;
            case "VS":
                cbbOperation.setVisible(true);
                cbbChange.setVisible(true);
                if ("Dice Change".equals(cbbA.getSelectedItem().toString())) {
                    cbbOperation.setModel(new DefaultComboBoxModel<>(new String[]{"only VS"}));
                } else {
                    txtAddDMG.setVisible(true);
                    cbbOperation.setModel(new DefaultComboBoxModel<>(new String[]{"[VAL] VS", "only VS"}));
                }
                cbbChange.setModel(new DefaultComboBoxModel<>(new String[]{"Ava", "DN", "Kot", "Merc", "Neh", "Noz", "Pir", "RL", "SH", "SL", "Temp", "WT", "Zil", "Bard", "Berserker", "Colossus", "Craftsman", "Mage", "Marauder", "Priest", "UnkClass", "Warrior", "Beast", "Dais", "Demon", "Dragon", "Elfine", "Eltarite", "Golem", "Guem", "Homchai", "Human", "Ice Elf", "Solarian", "Undead", "UnkRace"}));

                lblCounter.setVisible(false);
                txtShield.setVisible(false);
                counter.setVisible(false);

                lblAnotherWall.setVisible(false);
                lblAddAnother.setVisible(false);
                cbbAnotherAddDMG.setVisible(true);
                cbbAnotherAddDMG.setSelectedIndex(0);
                cbbAnotherAddDMG.setModel(new DefaultComboBoxModel<>(new String[]{"No Add Effect", "another VS"}));
                txtAnotherAddDMG.setVisible(false);
                cbbAnotherOperation.setVisible(false);
                break;
            case "Ally":
                cbbOperation.setVisible(true);
                cbbChange.setVisible(true);
                txtShield.setVisible(false);
                cbbOperation.setModel(new DefaultComboBoxModel<>(new String[]{"[VAL] x", "+[VAL] per", "-[VAL] per"}));
                cbbChange.setModel(new DefaultComboBoxModel<>(new String[]{"Ally", "Ava", "DN", "Kot", "Merc", "Neh", "Noz", "Pir", "RL", "SH", "SL", "Temp", "WT", "Zil", "Bard", "Berserker", "Colossus", "Craftsman", "Mage", "Marauder", "Priest", "UnkClass", "Warrior", "Beast", "Dais", "Demon", "Dragon", "Elfine", "Eltarite", "Golem", "Guem", "Homchai", "Human", "Ice Elf", "Solarian", "Undead", "UnkRace"}));
                txtAddDMG.setVisible(false);
                lblCounter.setVisible(true);
                if (cbbChange.getSelectedIndex() != 0) {
                    lblCounter.setText("How many " + cbbChange.getSelectedItem() + " allies?");
                } else {
                    lblCounter.setText("How many allies?");
                }
                counter.setVisible(true);
                counter.setModel(new javax.swing.SpinnerNumberModel((int) counter.getValue(), 0, 2, 1));

                lblAnotherWall.setVisible(true);
                lblAddAnother.setVisible(true);
                cbbAnotherAddDMG.setVisible(true);
                cbbAnotherAddDMG.setModel(new DefaultComboBoxModel<>(new String[]{"No Add Effect", "[VAL] VS", "[VAL] if atk", "[VAL] if dfd"}));
                if (cbbAnotherAddDMG.getSelectedIndex() != 0) {
                    txtAnotherAddDMG.setVisible(true);
                    cbbAnotherOperation.setVisible(true);
                } else {
                    txtAnotherAddDMG.setVisible(false);
                    cbbAnotherOperation.setVisible(false);
                }
                break;
            case "Opp":
                cbbOperation.setVisible(true);
                cbbChange.setVisible(true);
                txtShield.setVisible(false);
                cbbOperation.setModel(new DefaultComboBoxModel<>(new String[]{"[VAL] x", "+[VAL] per", "-[VAL] per"}));
                cbbChange.setModel(new DefaultComboBoxModel<>(new String[]{"Opp", "Ava", "DN", "Kot", "Merc", "Neh", "Noz", "Pir", "RL", "SH", "SL", "Temp", "WT", "Zil", "Bard", "Berserker", "Colossus", "Craftsman", "Mage", "Marauder", "Priest", "UnkClass", "Warrior", "Beast", "Dais", "Demon", "Dragon", "Elfine", "Eltarite", "Golem", "Guem", "Homchai", "Human", "Ice Elf", "Solarian", "Undead", "UnkRace"}));
                txtAddDMG.setVisible(false);
                lblCounter.setVisible(true);
                if (cbbChange.getSelectedIndex() != 0) {
                    lblCounter.setText("How many " + cbbChange.getSelectedItem() + " opps?");
                } else {
                    lblCounter.setText("How many opps?");
                }
                counter.setVisible(true);
                counter.setModel(new javax.swing.SpinnerNumberModel((int) counter.getValue(), 0, 3, 1));

                lblAnotherWall.setVisible(true);
                lblAddAnother.setVisible(true);
                cbbAnotherAddDMG.setVisible(true);
                cbbAnotherAddDMG.setModel(new DefaultComboBoxModel<>(new String[]{"No Add Effect", "[VAL] VS", "[VAL] if atk", "[VAL] if dfd"}));
                if (cbbAnotherAddDMG.getSelectedIndex() != 0) {
                    txtAnotherAddDMG.setVisible(true);
                    cbbAnotherOperation.setVisible(true);
                } else {
                    txtAnotherAddDMG.setVisible(false);
                    cbbAnotherOperation.setVisible(false);
                }
                break;
            case "Attacker":
                cbbOperation.setVisible(true);
                txtShield.setVisible(false);
                cbbChange.setVisible(false);
                if ("Dice Change".equals(cbbA.getSelectedItem().toString())) {
                    cbbOperation.setModel(new DefaultComboBoxModel<>(new String[]{"only if atk"}));
                } else {
                    cbbOperation.setModel(new DefaultComboBoxModel<>(new String[]{"[VAL] if atk", "only if atk"}));
                    txtAddDMG.setVisible(true);
                }
                lblCounter.setVisible(false);
                counter.setVisible(false);

                lblAnotherWall.setVisible(false);
                lblAddAnother.setVisible(false);
                cbbAnotherAddDMG.setVisible(false);
                cbbAnotherAddDMG.setSelectedIndex(0);
                txtAnotherAddDMG.setVisible(false);
                cbbAnotherOperation.setVisible(false);
                break;
            case "Defender":
                cbbOperation.setVisible(true);
                txtShield.setVisible(false);
                cbbChange.setVisible(false);
                if ("Dice Change".equals(cbbA.getSelectedItem().toString())) {
                    cbbOperation.setModel(new DefaultComboBoxModel<>(new String[]{"only if dfd"}));
                } else {
                    cbbOperation.setModel(new DefaultComboBoxModel<>(new String[]{"[VAL] if dfd", "only if dfd"}));
                    txtAddDMG.setVisible(true);
                }
                lblCounter.setVisible(false);
                counter.setVisible(false);

                lblAnotherWall.setVisible(false);
                lblAddAnother.setVisible(false);
                cbbAnotherAddDMG.setVisible(false);
                cbbAnotherAddDMG.setSelectedIndex(0);
                txtAnotherAddDMG.setVisible(false);
                cbbAnotherOperation.setVisible(false);
                break;
            case "Bonus/Malus":
                cbbOperation.setVisible(true);
                cbbChange.setVisible(true);
                if (!"DejaPAing".equals(eraoque[0])) {
                    cbbOperation.setModel(new DefaultComboBoxModel<>(new String[]{"[VAL] x", "[VAL] +", "[VAL] -", "/ [VAL]", "+[VAL] per", "-[VAL] per"}));
                } else {
                    cbbOperation.setModel(new DefaultComboBoxModel<>(new String[]{"[VAL] x", "+[VAL] per", "-[VAL] per"}));
                }
                cbbChange.setModel(new DefaultComboBoxModel<>(new String[]{"Berserk", "Blessing", "Blizzard", "Bulwark", "Critical", "+DMG", "-DMG", "Dodge", "Eclipse", "Frostbite", "Ice", "Powder", "Rage", "Resilience", "Riposte", "Rune", "Scarab", "Shield", "Stench", "Storm", "Strength", "Terror", "Thorn", "Thunderstruck"}));
                txtAddDMG.setVisible(false);
                lblCounter.setVisible(false);
                counter.setVisible(false);

                lblAnotherWall.setVisible(true);
                lblAddAnother.setVisible(true);
                cbbAnotherAddDMG.setVisible(true);
                cbbAnotherAddDMG.setModel(new DefaultComboBoxModel<>(new String[]{"No Add Effect", "[VAL] VS", "[VAL] if atk", "[VAL] if dfd"}));
                if (cbbAnotherAddDMG.getSelectedIndex() != 0) {
                    txtAnotherAddDMG.setVisible(true);
                    cbbAnotherOperation.setVisible(true);
                } else {
                    txtAnotherAddDMG.setVisible(false);
                    cbbAnotherOperation.setVisible(false);
                }
                break;
            case "opp Bonus/Malus":
                cbbOperation.setVisible(true);
                cbbChange.setVisible(true);
                if (!"DejaPAing".equals(eraoque[0])) {
                    cbbOperation.setModel(new DefaultComboBoxModel<>(new String[]{"[VAL] x", "[VAL] +", "[VAL] -", "/ [VAL]", "+[VAL] per", "-[VAL] per"}));
                } else {
                    cbbOperation.setModel(new DefaultComboBoxModel<>(new String[]{"[VAL] x", "+[VAL] per", "-[VAL] per"}));
                }
                cbbChange.setModel(new DefaultComboBoxModel<>(new String[]{"Berserk", "Blessing", "Blizzard", "Bulwark", "Critical", "+DMG", "-DMG", "Dodge", "Eclipse", "Frostbite", "Ice", "Powder", "Rage", "Resilience", "Riposte", "Rune", "Scarab", "Shield", "Stench", "Storm", "Strength", "Terror", "Thorn", "Thunderstruck"}));
                txtAddDMG.setVisible(false);
                lblCounter.setVisible(false);
                counter.setVisible(false);
                txtShield.setVisible(false);

                lblAnotherWall.setVisible(true);
                lblAddAnother.setVisible(true);
                cbbAnotherAddDMG.setVisible(true);
                cbbAnotherAddDMG.setModel(new DefaultComboBoxModel<>(new String[]{"No Add Effect", "[VAL] VS", "[VAL] if atk", "[VAL] if dfd"}));
                if (cbbAnotherAddDMG.getSelectedIndex() != 0) {
                    txtAnotherAddDMG.setVisible(true);
                    cbbAnotherOperation.setVisible(true);
                } else {
                    txtAnotherAddDMG.setVisible(false);
                    cbbAnotherOperation.setVisible(false);
                }
                break;
            case "if":
                cbbOperation.setVisible(false);
                cbbChange.setVisible(true);
                txtShield.setVisible(false);
                cbbChange.setModel(new DefaultComboBoxModel<>(new String[]{"Ava", "DN", "Kot", "Merc", "Neh", "Noz", "Pir", "RL", "SH", "SL", "Temp", "WT", "Zil", "Bard", "Berserker", "Colossus", "Craftsman", "Mage", "Marauder", "Priest", "UnkClass", "Warrior", "Beast", "Dais", "Demon", "Dragon", "Elfine", "Eltarite", "Golem", "Guem", "Homchai", "Human", "Ice Elf", "Solarian", "Undead", "UnkRace"}));
                txtAddDMG.setVisible(true);
                lblCounter.setVisible(false);
                counter.setVisible(false);

                lblAnotherWall.setVisible(false);
                lblAddAnother.setVisible(false);
                cbbAnotherAddDMG.setVisible(false);
                cbbAnotherAddDMG.setSelectedIndex(0);
                break;
        }
    }

    private void tradeTopToMid() {
        String aux[];

        aux = skills[6];
        skills[6] = skills[3];
        skills[3] = aux;

        aux = skills[7];
        skills[7] = skills[4];
        skills[4] = aux;

        aux = skills[8];
        skills[8] = skills[5];
        skills[5] = aux;

        String newaux;

        newaux = eraoque[6];
        eraoque[6] = eraoque[3];
        eraoque[3] = newaux;

        newaux = eraoque[7];
        eraoque[7] = eraoque[4];
        eraoque[4] = newaux;

        newaux = eraoque[8];
        eraoque[8] = eraoque[5];
        eraoque[5] = newaux;

        int diceaux;

        diceaux = TD1.getSelectedIndex();
        TD1.setSelectedIndex(MD1.getSelectedIndex());
        MD1.setSelectedIndex(diceaux);

        diceaux = TD2.getSelectedIndex();
        TD2.setSelectedIndex(MD2.getSelectedIndex());
        MD2.setSelectedIndex(diceaux);

        diceaux = TD3.getSelectedIndex();
        TD3.setSelectedIndex(MD3.getSelectedIndex());
        MD3.setSelectedIndex(diceaux);

        diceaux = TD4.getSelectedIndex();
        TD4.setSelectedIndex(MD4.getSelectedIndex());
        MD4.setSelectedIndex(diceaux);

        pBuffing.setVisible(false);
        pAssuming.setVisible(false);
        pSkill.setVisible(false);
        pAuraSeal.setVisible(false);
        allPtoClear();

        if ("not".equals(skills[7][0])) {
            pT2.setVisible(false);
            btnTMinus.setEnabled(false);
            btnTPlus.setEnabled(true);
        } else {
            pT2.setVisible(true);
            btnTPlus.setEnabled(true);
            btnTMinus.setEnabled(true);
        }
        if ("not".equals(skills[8][0])) {
            pT3.setVisible(false);
            btnTPlus.setEnabled(true);
        } else {
            pT3.setVisible(true);
            btnTPlus.setEnabled(false);
        }

        if ("not".equals(skills[4][0])) {
            pM2.setVisible(false);
            btnMMinus.setEnabled(false);
            btnMPlus.setEnabled(true);
        } else {
            pM2.setVisible(true);
            btnMPlus.setEnabled(true);
            btnMMinus.setEnabled(true);
        }
        if ("not".equals(skills[5][0])) {
            pM3.setVisible(false);
            btnMPlus.setEnabled(true);
        } else {
            pM3.setVisible(true);
            btnMPlus.setEnabled(false);
        }

        updateLabels();
    }

    private void tradeBotToMid() {
        String aux[];

        aux = skills[0];
        skills[0] = skills[3];
        skills[3] = aux;

        aux = skills[1];
        skills[1] = skills[4];
        skills[4] = aux;

        aux = skills[2];
        skills[2] = skills[5];
        skills[5] = aux;

        String newaux;

        newaux = eraoque[0];
        eraoque[0] = eraoque[3];
        eraoque[3] = newaux;

        newaux = eraoque[1];
        eraoque[1] = eraoque[4];
        eraoque[4] = newaux;

        newaux = eraoque[2];
        eraoque[2] = eraoque[5];
        eraoque[5] = newaux;

        int diceaux;

        diceaux = BD1.getSelectedIndex();
        BD1.setSelectedIndex(MD1.getSelectedIndex());
        MD1.setSelectedIndex(diceaux);

        diceaux = BD2.getSelectedIndex();
        BD2.setSelectedIndex(MD2.getSelectedIndex());
        MD2.setSelectedIndex(diceaux);

        diceaux = BD3.getSelectedIndex();
        BD3.setSelectedIndex(MD3.getSelectedIndex());
        MD3.setSelectedIndex(diceaux);

        diceaux = BD4.getSelectedIndex();
        BD4.setSelectedIndex(MD4.getSelectedIndex());
        MD4.setSelectedIndex(diceaux);

        pBuffing.setVisible(false);
        pAssuming.setVisible(false);
        pSkill.setVisible(false);
        pAuraSeal.setVisible(false);
        allPtoClear();

        if ("not".equals(skills[1][0])) {
            pB2.setVisible(false);
            btnBMinus.setEnabled(false);
            btnBPlus.setEnabled(true);
        } else {
            pB2.setVisible(true);
            btnBPlus.setEnabled(true);
            btnBMinus.setEnabled(true);
        }
        if ("not".equals(skills[2][0])) {
            pB3.setVisible(false);
            btnBPlus.setEnabled(true);
        } else {
            pB3.setVisible(true);
            btnBPlus.setEnabled(false);
        }

        if ("not".equals(skills[4][0])) {
            pM2.setVisible(false);
            btnMMinus.setEnabled(false);
            btnMPlus.setEnabled(true);
        } else {
            pM2.setVisible(true);
            btnMPlus.setEnabled(true);
            btnMMinus.setEnabled(true);
        }
        if ("not".equals(skills[5][0])) {
            pM3.setVisible(false);
            btnMPlus.setEnabled(true);
        } else {
            pM3.setVisible(true);
            btnMPlus.setEnabled(false);
        }

        updateLabels();
    }

    private void allPtoClear() {
        pT1.setBackground(new Color(79, 116, 159));
        pT2.setBackground(new Color(79, 116, 159));
        pT3.setBackground(new Color(79, 116, 159));
        pM1.setBackground(new Color(79, 116, 159));
        pM2.setBackground(new Color(79, 116, 159));
        pM3.setBackground(new Color(79, 116, 159));
        pB1.setBackground(new Color(79, 116, 159));
        pB2.setBackground(new Color(79, 116, 159));
        pB3.setBackground(new Color(79, 116, 159));
    }

    private void minimando() {
        if ("".equals(txtN.getText()) || "0".equals(txtN.getText())) {
            txtN.setText("1");
        }

        if ("".equals(txtV.getText())) {
            txtV.setText("0");
        }

        if ("".equals(txtAddDMG.getText())) {
            txtAddDMG.setText("0");
        }

        if ("".equals(txtAnotherAddDMG.getText())) {
            txtAnotherAddDMG.setText("0");
        }

        if ("".equals(txtOBerserk.getText())) {
            txtOBerserk.setText("0");
        }
        if ("".equals(txtOBlessing.getText())) {
            txtOBlessing.setText("0");
        }
        if ("".equals(txtOBlizzard.getText())) {
            txtOBlizzard.setText("0");
        }
        if ("".equals(txtOBulwark.getText())) {
            txtOBulwark.setText("0");
        }
        if ("".equals(txtOCritical.getText())) {
            txtOCritical.setText("0");
        }
        if ("".equals(txtODMGBonus.getText())) {
            txtODMGBonus.setText("0");
        }
        if ("".equals(txtODMGMalus.getText())) {
            txtODMGMalus.setText("0");
        }
        if ("".equals(txtODodge.getText())) {
            txtODodge.setText("0");
        }
        if ("".equals(txtOEclipse.getText())) {
            txtOEclipse.setText("0");
        }
        if ("".equals(txtOFrostbite.getText())) {
            txtOFrostbite.setText("0");
        }
        if ("".equals(txtOIce.getText())) {
            txtOIce.setText("0");
        }
        if ("".equals(txtOPowder.getText())) {
            txtOPowder.setText("0");
        }
        if ("".equals(txtORage.getText())) {
            txtORage.setText("0");
        }
        if ("".equals(txtOStorm.getText())) {
            txtOStorm.setText("0");
        }
        if ("".equals(txtOResilience.getText())) {
            txtOResilience.setText("0");
        }
        if ("".equals(txtORiposte.getText())) {
            txtORiposte.setText("0");
        }
        if ("".equals(txtORune.getText())) {
            txtORune.setText("0");
        }
        if ("".equals(txtOScarab.getText())) {
            txtOScarab.setText("0");
        }
        if ("".equals(txtOShield.getText())) {
            txtOShield.setText("0");
        }
        if ("".equals(txtOStench.getText())) {
            txtOStench.setText("0");
        }
        if ("".equals(txtOTerror.getText())) {
            txtOTerror.setText("0");
        }
        if ("".equals(txtOThorn.getText())) {
            txtOThorn.setText("0");
        }
        if ("".equals(txtOThunderstruck.getText())) {
            txtOThunderstruck.setText("0");
        }
        if ("".equals(txtOSTRBonus.getText())) {
            txtOSTRBonus.setText("0");
        }
        if ("".equals(txtOSTRMalus.getText())) {
            txtOSTRMalus.setText("0");
        }

        if ("".equals(txtYBerserk.getText())) {
            txtYBerserk.setText("0");
        }
        if ("".equals(txtYBlessing.getText())) {
            txtYBlessing.setText("0");
        }
        if ("".equals(txtYBlizzard.getText())) {
            txtYBlizzard.setText("0");
        }
        if ("".equals(txtYBulwark.getText())) {
            txtYBulwark.setText("0");
        }
        if ("".equals(txtYCritical.getText())) {
            txtYCritical.setText("0");
        }
        if ("".equals(txtYDMGBonus.getText())) {
            txtYDMGBonus.setText("0");
        }
        if ("".equals(txtYDMGMalus.getText())) {
            txtYDMGMalus.setText("0");
        }
        if ("".equals(txtYDodge.getText())) {
            txtYDodge.setText("0");
        }
        if ("".equals(txtYEclipse.getText())) {
            txtYEclipse.setText("0");
        }
        if ("".equals(txtYFrostbite.getText())) {
            txtYFrostbite.setText("0");
        }
        if ("".equals(txtYIce.getText())) {
            txtYIce.setText("0");
        }
        if ("".equals(txtYPowder.getText())) {
            txtYPowder.setText("0");
        }
        if ("".equals(txtYRage.getText())) {
            txtYRage.setText("0");
        }
        if ("".equals(txtYResilience.getText())) {
            txtYResilience.setText("0");
        }
        if ("".equals(txtYRiposte.getText())) {
            txtYRiposte.setText("0");
        }
        if ("".equals(txtYRune.getText())) {
            txtYRune.setText("0");
        }
        if ("".equals(txtYScarab.getText())) {
            txtYScarab.setText("0");
        }
        if ("".equals(txtYShield.getText())) {
            txtYShield.setText("0");
        }
        if ("".equals(txtYStench.getText())) {
            txtYStench.setText("0");
        }

        if ("".equals(txtYStorm.getText())) {
            txtYStorm.setText("0");
        }
        if ("".equals(txtYTerror.getText())) {
            txtYTerror.setText("0");
        }
        if ("".equals(txtYThorn.getText())) {
            txtYThorn.setText("0");
        }
        if ("".equals(txtYThunderstruck.getText())) {
            txtYThunderstruck.setText("0");
        }
        if ("".equals(txtYSTRBonus.getText())) {
            txtYSTRBonus.setText("0");
        }
        if ("".equals(txtYSTRMalus.getText())) {
            txtYSTRMalus.setText("0");
        }

        if ("".equals(txtStr.getText())) {
            txtStr.setText("0");
        }
        if ("".equals(txtOppStr.getText())) {
            txtOppStr.setText("0");
        }
        if ("".equals(txtShield.getText())) {
            txtShield.setText("0");
        }
    }

    private void updateAurasSealsText() {
        if (NoSeal.isSelected()) {
            lblTextSeal.setIcon(null);
            lblTextSeal.setText("None");
        } else if (DMGSeal.isSelected()) {
            lblTextSeal.setIcon(createImageIcon("/IMG/sealDMG.png"));
            lblTextSeal.setText("DMG +50");
        } else if (DodgeSeal.isSelected()) {
            lblTextSeal.setIcon(createImageIcon("/IMG/sealDodge.png"));
            lblTextSeal.setText("Dodge 1");
        } else if (RageSeal.isSelected()) {
            lblTextSeal.setIcon(createImageIcon("/IMG/sealRage.png"));
            lblTextSeal.setText("Rage 20");
        } else if (ResilienceSeal.isSelected()) {
            lblTextSeal.setIcon(createImageIcon("/IMG/sealResilience.png"));
            lblTextSeal.setText("Resilience 80");
        } else if (ShieldSeal.isSelected()) {
            lblTextSeal.setIcon(createImageIcon("/IMG/sealShield.png"));
            lblTextSeal.setText("Shield 400");
        } else if (StenchSeal.isSelected()) {
            lblTextSeal.setIcon(createImageIcon("/IMG/sealStench.png"));
            lblTextSeal.setText("Stench 30");
        } else if (StrengthSeal.isSelected()) {
            lblTextSeal.setIcon(createImageIcon("/IMG/sealStrength.png"));
            lblTextSeal.setText("Strength +60");
        } else if (ThornSeal.isSelected()) {
            lblTextSeal.setIcon(createImageIcon("/IMG/sealThorn.png"));
            lblTextSeal.setText("Thorn 70");
        } else if (BlessingSeal.isSelected()) {
            lblTextSeal.setIcon(null);
            lblTextSeal.setText("Blessing 20");
        }

        ArrayList<String> auras = new ArrayList<>();

        if (checkBerserkAura.isSelected()) {
            auras.add("Berserk");
        }
        if (checkBonusDMGAura.isSelected()) {
            auras.add("+DMG");
        }
        if (checkDodgeAura.isSelected()) {
            auras.add("Dodge");
        }
        if (checkFrostbiteAura.isSelected()) {
            auras.add("Frostbite");
        }
        if (checkHealAura.isSelected()) {
            auras.add("Heal");
        }
        if (checkIceAura.isSelected()) {
            auras.add("Ice");
        }
        if (checkMalusAura.isSelected()) {
            auras.add("Malus");
        }
        if (checkRageAura.isSelected()) {
            auras.add("Rage");
        }
        if (checkResilienceAura.isSelected()) {
            auras.add("Resilience");
        }
        if (checkShieldAura.isSelected()) {
            auras.add("Shield");
        }
        if (checkStrAura.isSelected()) {
            auras.add("Strength");
        }
        if (checkThornAura.isSelected()) {
            auras.add("Thorn");
        }
        if (checkThunderstruckAura.isSelected()) {
            auras.add("Thunderstruck");
        }

        if (auras.size() >= 3) {
            lblAura.setText(" Aura(s): " + auras.size() + " active auras");
        } else if (auras.size() == 2) {
            lblAura.setText(" Aura(s): " + auras.get(0) + " & " + auras.get(1));
        } else if (auras.size() == 1) {
            lblAura.setText(" Aura(s): " + auras.get(0));
        } else {
            lblAura.setText(" Aura(s): None");
        }
    }

    private void openizando(String savedset) {
        String analyze = "";
        for (int k = 0; k < 10; k++) {
            analyze += savedset.charAt(k);
        }
        int kount = 0;
        if ("2020_06_12".equals(analyze)) {
            kount = 122;
        } else if ("2020_07_05".equals(analyze)) {
            kount = 125;
        }

        Arrays.fill(openarray, "");
        int charcount = 0;
        for (int k = 0; k < kount; k++) {

            while (!"~".equals(String.valueOf(savedset.charAt(charcount)))) {
                openarray[k] += savedset.charAt(charcount);
                charcount += 1;
            }
            charcount += 1;
        }

        if ("2020_06_12".equals(openarray[0])) {
            txtStr.setText(openarray[1]);
            txtOppStr.setText(openarray[2]);
            cbbRace.setSelectedIndex(Integer.parseInt(openarray[3]));
            cbbOppRace.setSelectedIndex(Integer.parseInt(openarray[4]));
            cbbClass.setSelectedIndex(Integer.parseInt(openarray[5]));
            cbbOppClass.setSelectedIndex(Integer.parseInt(openarray[6]));
            cbbGuild.setSelectedIndex(Integer.parseInt(openarray[7]));
            cbbOppGuild.setSelectedIndex(Integer.parseInt(openarray[8]));

            if ("1".equals(openarray[120])) {
                checkExperimental.setSelected(true);
            }

            BD1.setSelectedIndex(Integer.parseInt(openarray[10]));
            BD2.setSelectedIndex(Integer.parseInt(openarray[11]));
            BD3.setSelectedIndex(Integer.parseInt(openarray[12]));
            BD4.setSelectedIndex(Integer.parseInt(openarray[13]));

            separando(0, openarray[15]);
            separando(1, openarray[17]);
            separando(2, openarray[19]);

            if ("yes".equals(skills[1][0])) {
                pB2.setVisible(true);
            }
            if ("yes".equals(skills[2][0])) {
                pB3.setVisible(true);
            }

            MD1.setSelectedIndex(Integer.parseInt(openarray[21]));
            MD2.setSelectedIndex(Integer.parseInt(openarray[22]));
            MD3.setSelectedIndex(Integer.parseInt(openarray[23]));
            MD4.setSelectedIndex(Integer.parseInt(openarray[24]));

            separando(3, openarray[26]);
            separando(4, openarray[28]);
            separando(5, openarray[30]);

            if ("yes".equals(skills[4][0])) {
                pM2.setVisible(true);
            }
            if ("yes".equals(skills[5][0])) {
                pM3.setVisible(true);
            }

            TD1.setSelectedIndex(Integer.parseInt(openarray[32]));
            TD2.setSelectedIndex(Integer.parseInt(openarray[33]));
            TD3.setSelectedIndex(Integer.parseInt(openarray[34]));
            TD4.setSelectedIndex(Integer.parseInt(openarray[35]));

            separando(6, openarray[37]);
            separando(7, openarray[39]);
            separando(8, openarray[41]);

            if ("yes".equals(skills[7][0])) {
                pT2.setVisible(true);
            }
            if ("yes".equals(skills[8][0])) {
                pT3.setVisible(true);
            }

            if ("0".equals(openarray[42])) {
                rbtDfd.setSelected(true);
            }
            countBlizz1.setValue(Integer.parseInt(openarray[43]));
            countBlizz2.setValue(Integer.parseInt(openarray[44]));
            countBlizz3.setValue(Integer.parseInt(openarray[45]));
            countActiv1.setValue(Integer.parseInt(openarray[46]));
            countActiv2.setValue(Integer.parseInt(openarray[47]));
            countActiv3.setValue(Integer.parseInt(openarray[48]));
            countHit1.setValue(Integer.parseInt(openarray[49]));
            countHit2.setValue(Integer.parseInt(openarray[50]));
            countHit3.setValue(Integer.parseInt(openarray[51]));
            countHitS.setValue(Integer.parseInt(openarray[52]));

            switch (Integer.parseInt(openarray[53])) {
                case 1:
                    DMGSeal.setSelected(true);
                    break;
                case 2:
                    DodgeSeal.setSelected(true);
                    break;
                case 3:
                    RageSeal.setSelected(true);
                    break;
                case 4:
                    ResilienceSeal.setSelected(true);
                    break;
                case 5:
                    ShieldSeal.setSelected(true);
                    break;
                case 6:
                    StenchSeal.setSelected(true);
                    break;
                case 7:
                    StrengthSeal.setSelected(true);
                    break;
                case 8:
                    ThornSeal.setSelected(true);
                    break;
                case 9:
                    BlessingSeal.setSelected(true);
                    break;
            }

            if ("1".equals(openarray[54])) {
                checkBerserkAura.setSelected(true);
            }
            if ("1".equals(openarray[55])) {
                checkBonusDMGAura.setSelected(true);
            }
            if ("1".equals(openarray[56])) {
                checkDodgeAura.setSelected(true);
            }
            if ("1".equals(openarray[57])) {
                checkFrostbiteAura.setSelected(true);
            }
            if ("1".equals(openarray[58])) {
                checkHealAura.setSelected(true);
            }
            if ("1".equals(openarray[59])) {
                checkIceAura.setSelected(true);
            }
            if ("1".equals(openarray[60])) {
                checkMalusAura.setSelected(true);
            }
            if ("1".equals(openarray[61])) {
                checkRageAura.setSelected(true);
            }
            if ("1".equals(openarray[62])) {
                checkResilienceAura.setSelected(true);
            }
            if ("1".equals(openarray[63])) {
                checkShieldAura.setSelected(true);
            }
            if ("1".equals(openarray[64])) {
                checkStrAura.setSelected(true);
            }
            if ("1".equals(openarray[65])) {
                checkThornAura.setSelected(true);
            }
            if ("1".equals(openarray[66])) {
                checkThunderstruckAura.setSelected(true);
            }

            txtYBerserk.setText(openarray[67]);
            txtYBlessing.setText(openarray[68]);
            txtYBlizzard.setText(openarray[69]);
            txtYBulwark.setText(openarray[70]);
            txtYCritical.setText(openarray[71]);
            txtYDMGBonus.setText(openarray[72]);
            txtYDMGMalus.setText(openarray[73]);
            txtYDodge.setText(openarray[74]);
            txtYEclipse.setText(openarray[75]);
            txtYFrostbite.setText(openarray[76]);
            txtYIce.setText(openarray[77]);
            txtYPowder.setText(openarray[78]);
            txtYRage.setText(openarray[79]);
            txtYResilience.setText(openarray[80]);
            txtYRiposte.setText(openarray[81]);
            txtYRune.setText(openarray[82]);
            txtYSTRBonus.setText(openarray[83]);
            txtYSTRMalus.setText(openarray[84]);
            txtYScarab.setText(openarray[85]);
            txtYShield.setText(openarray[86]);
            txtYStench.setText(openarray[87]);
            txtYTerror.setText(openarray[88]);
            txtYThorn.setText(openarray[89]);
            txtYThunderstruck.setText(openarray[90]);

            txtOBerserk.setText(openarray[91]);
            txtOBlessing.setText(openarray[92]);
            txtOBlizzard.setText(openarray[93]);
            txtOBulwark.setText(openarray[94]);
            txtOCritical.setText(openarray[95]);
            txtODMGBonus.setText(openarray[96]);
            txtODMGMalus.setText(openarray[97]);
            txtODodge.setText(openarray[98]);
            txtOEclipse.setText(openarray[99]);
            txtOFrostbite.setText(openarray[100]);
            txtOIce.setText(openarray[101]);
            txtOPowder.setText(openarray[102]);
            txtORage.setText(openarray[103]);
            txtOResilience.setText(openarray[104]);
            txtORiposte.setText(openarray[105]);
            txtORune.setText(openarray[106]);
            txtOSTRBonus.setText(openarray[107]);
            txtOSTRMalus.setText(openarray[108]);
            txtOScarab.setText(openarray[109]);
            txtOShield.setText(openarray[110]);
            txtOStench.setText(openarray[111]);
            txtOTerror.setText(openarray[112]);
            txtOThorn.setText(openarray[113]);
            txtOThunderstruck.setText(openarray[114]);

            if (cbbRace.getSelectedIndex() == 4 || "Symbiosis".equals(skills[0][7]) || "Symbiosis".equals(skills[1][7]) || "Symbiosis".equals(skills[2][7]) || "Symbiosis".equals(skills[3][7]) || "Symbiosis".equals(skills[4][7]) || "Symbiosis".equals(skills[5][7]) || "Symbiosis".equals(skills[6][7]) || "Symbiosis".equals(skills[7][7]) || "Symbiosis".equals(skills[8][7])) {
                checkSymb.setVisible(true);
            }

            if ("1".equals(openarray[115])) {
                checkSymb.setSelected(true);
            }
            if ("1".equals(openarray[116])) {
                checkExtra.setSelected(true);
            }
            if ("1".equals(openarray[117])) {
                checkShield.setSelected(true);
            }
            if ("1".equals(openarray[118])) {
                checkHeal.setSelected(true);
            }
            if ("1".equals(openarray[119])) {
                checkBlizz.setSelected(true);
            }

            int charcountC = 0;
            for (int k = 0; k < 9; k++) {
                String botrray = "";
                while (!"#".equals(String.valueOf(openarray[121].charAt(charcountC)))) {
                    botrray += openarray[121].charAt(charcountC);
                    charcountC += 1;
                }
                eraoque[k] = botrray;
                charcountC += 1;

            }

        } else if ("2020_07_05".equals(openarray[0])) {
            txtStr.setText(openarray[1]);
            txtOppStr.setText(openarray[2]);
            cbbRace.setSelectedIndex(Integer.parseInt(openarray[3]));
            cbbOppRace.setSelectedIndex(Integer.parseInt(openarray[4]));
            cbbClass.setSelectedIndex(Integer.parseInt(openarray[5]));
            cbbOppClass.setSelectedIndex(Integer.parseInt(openarray[6]));
            cbbGuild.setSelectedIndex(Integer.parseInt(openarray[7]));
            cbbOppGuild.setSelectedIndex(Integer.parseInt(openarray[8]));

            if ("1".equals(openarray[120])) {
                checkExperimental.setSelected(true);
            }

            BD1.setSelectedIndex(Integer.parseInt(openarray[10]));
            BD2.setSelectedIndex(Integer.parseInt(openarray[11]));
            BD3.setSelectedIndex(Integer.parseInt(openarray[12]));
            BD4.setSelectedIndex(Integer.parseInt(openarray[13]));

            separando(0, openarray[15]);
            separando(1, openarray[17]);
            separando(2, openarray[19]);

            if ("yes".equals(skills[1][0])) {
                pB2.setVisible(true);
            }
            if ("yes".equals(skills[2][0])) {
                pB3.setVisible(true);
            }

            MD1.setSelectedIndex(Integer.parseInt(openarray[21]));
            MD2.setSelectedIndex(Integer.parseInt(openarray[22]));
            MD3.setSelectedIndex(Integer.parseInt(openarray[23]));
            MD4.setSelectedIndex(Integer.parseInt(openarray[24]));

            separando(3, openarray[26]);
            separando(4, openarray[28]);
            separando(5, openarray[30]);

            if ("yes".equals(skills[4][0])) {
                pM2.setVisible(true);
            }
            if ("yes".equals(skills[5][0])) {
                pM3.setVisible(true);
            }

            TD1.setSelectedIndex(Integer.parseInt(openarray[32]));
            TD2.setSelectedIndex(Integer.parseInt(openarray[33]));
            TD3.setSelectedIndex(Integer.parseInt(openarray[34]));
            TD4.setSelectedIndex(Integer.parseInt(openarray[35]));

            separando(6, openarray[37]);
            separando(7, openarray[39]);
            separando(8, openarray[41]);

            if ("yes".equals(skills[7][0])) {
                pT2.setVisible(true);
            }
            if ("yes".equals(skills[8][0])) {
                pT3.setVisible(true);
            }

            if ("0".equals(openarray[42])) {
                rbtDfd.setSelected(true);
            }
            countBlizz1.setValue(Integer.parseInt(openarray[43]));
            countBlizz2.setValue(Integer.parseInt(openarray[44]));
            countBlizz3.setValue(Integer.parseInt(openarray[45]));
            countActiv1.setValue(Integer.parseInt(openarray[46]));
            countActiv2.setValue(Integer.parseInt(openarray[47]));
            countActiv3.setValue(Integer.parseInt(openarray[48]));
            countHit1.setValue(Integer.parseInt(openarray[49]));
            countHit2.setValue(Integer.parseInt(openarray[50]));
            countHit3.setValue(Integer.parseInt(openarray[51]));
            countHitS.setValue(Integer.parseInt(openarray[52]));

            switch (Integer.parseInt(openarray[53])) {
                case 1:
                    DMGSeal.setSelected(true);
                    break;
                case 2:
                    DodgeSeal.setSelected(true);
                    break;
                case 3:
                    RageSeal.setSelected(true);
                    break;
                case 4:
                    ResilienceSeal.setSelected(true);
                    break;
                case 5:
                    ShieldSeal.setSelected(true);
                    break;
                case 6:
                    StenchSeal.setSelected(true);
                    break;
                case 7:
                    StrengthSeal.setSelected(true);
                    break;
                case 8:
                    ThornSeal.setSelected(true);
                    break;
                case 9:
                    BlessingSeal.setSelected(true);
                    break;
            }

            if ("1".equals(openarray[54])) {
                checkBerserkAura.setSelected(true);
            }
            if ("1".equals(openarray[55])) {
                checkBonusDMGAura.setSelected(true);
            }
            if ("1".equals(openarray[56])) {
                checkDodgeAura.setSelected(true);
            }
            if ("1".equals(openarray[57])) {
                checkFrostbiteAura.setSelected(true);
            }
            if ("1".equals(openarray[58])) {
                checkHealAura.setSelected(true);
            }
            if ("1".equals(openarray[59])) {
                checkIceAura.setSelected(true);
            }
            if ("1".equals(openarray[60])) {
                checkMalusAura.setSelected(true);
            }
            if ("1".equals(openarray[61])) {
                checkRageAura.setSelected(true);
            }
            if ("1".equals(openarray[62])) {
                checkResilienceAura.setSelected(true);
            }
            if ("1".equals(openarray[63])) {
                checkShieldAura.setSelected(true);
            }
            if ("1".equals(openarray[64])) {
                checkStrAura.setSelected(true);
            }
            if ("1".equals(openarray[65])) {
                checkThornAura.setSelected(true);
            }
            if ("1".equals(openarray[66])) {
                checkThunderstruckAura.setSelected(true);
            }

            txtYBerserk.setText(openarray[67]);
            txtYBlessing.setText(openarray[68]);
            txtYBlizzard.setText(openarray[69]);
            txtYBulwark.setText(openarray[70]);
            txtYCritical.setText(openarray[71]);
            txtYDMGBonus.setText(openarray[72]);
            txtYDMGMalus.setText(openarray[73]);
            txtYDodge.setText(openarray[74]);
            txtYEclipse.setText(openarray[75]);
            txtYFrostbite.setText(openarray[76]);
            txtYIce.setText(openarray[77]);
            txtYPowder.setText(openarray[78]);
            txtYRage.setText(openarray[79]);
            txtYResilience.setText(openarray[80]);
            txtYRiposte.setText(openarray[81]);
            txtYRune.setText(openarray[82]);
            txtYSTRBonus.setText(openarray[83]);
            txtYSTRMalus.setText(openarray[84]);
            txtYScarab.setText(openarray[85]);
            txtYShield.setText(openarray[86]);
            txtYStench.setText(openarray[87]);
            txtYTerror.setText(openarray[88]);
            txtYThorn.setText(openarray[89]);
            txtYThunderstruck.setText(openarray[90]);

            txtOBerserk.setText(openarray[91]);
            txtOBlessing.setText(openarray[92]);
            txtOBlizzard.setText(openarray[93]);
            txtOBulwark.setText(openarray[94]);
            txtOCritical.setText(openarray[95]);
            txtODMGBonus.setText(openarray[96]);
            txtODMGMalus.setText(openarray[97]);
            txtODodge.setText(openarray[98]);
            txtOEclipse.setText(openarray[99]);
            txtOFrostbite.setText(openarray[100]);
            txtOIce.setText(openarray[101]);
            txtOPowder.setText(openarray[102]);
            txtORage.setText(openarray[103]);
            txtOResilience.setText(openarray[104]);
            txtORiposte.setText(openarray[105]);
            txtORune.setText(openarray[106]);
            txtOSTRBonus.setText(openarray[107]);
            txtOSTRMalus.setText(openarray[108]);
            txtOScarab.setText(openarray[109]);
            txtOShield.setText(openarray[110]);
            txtOStench.setText(openarray[111]);
            txtOTerror.setText(openarray[112]);
            txtOThorn.setText(openarray[113]);
            txtOThunderstruck.setText(openarray[114]);

            if (cbbRace.getSelectedIndex() == 4 || "Symbiosis".equals(skills[0][7]) || "Symbiosis".equals(skills[1][7]) || "Symbiosis".equals(skills[2][7]) || "Symbiosis".equals(skills[3][7]) || "Symbiosis".equals(skills[4][7]) || "Symbiosis".equals(skills[5][7]) || "Symbiosis".equals(skills[6][7]) || "Symbiosis".equals(skills[7][7]) || "Symbiosis".equals(skills[8][7])) {
                checkSymb.setVisible(true);
            }

            if ("1".equals(openarray[115])) {
                checkSymb.setSelected(true);
            }
            if ("1".equals(openarray[116])) {
                checkExtra.setSelected(true);
            }
            if ("1".equals(openarray[117])) {
                checkShield.setSelected(true);
            }
            if ("1".equals(openarray[118])) {
                checkHeal.setSelected(true);
            }
            if ("1".equals(openarray[119])) {
                checkBlizz.setSelected(true);
            }

            int charcountC = 0;
            for (int k = 0; k < 9; k++) {
                String botrray = "";
                while (!"#".equals(String.valueOf(openarray[121].charAt(charcountC)))) {
                    botrray += openarray[121].charAt(charcountC);
                    charcountC += 1;
                }
                eraoque[k] = botrray;
                charcountC += 1;

            }

            counter1.setValue(Integer.parseInt(openarray[122]));

            if ("1".equals(openarray[123])) {
                rbtWinning.setSelected(true);
            }

            if ("1".equals(openarray[124])) {
                checkSupport.setSelected(true);
            }

        }
        updateLabels();
        allPtoClear();
        pSkill.setVisible(false);
        if ("not".equals(skills[1][0])) {
            btnBMinus.setEnabled(false);
        } else {
            btnBMinus.setEnabled(true);
        }
        if ("not".equals(skills[2][0])) {
            btnBPlus.setEnabled(true);
        } else {
            btnBPlus.setEnabled(false);
        }
        if ("not".equals(skills[4][0])) {
            btnMMinus.setEnabled(false);
        } else {
            btnMMinus.setEnabled(true);
        }
        if ("not".equals(skills[5][0])) {
            btnMPlus.setEnabled(true);
        } else {
            btnMPlus.setEnabled(false);
        }
        if ("not".equals(skills[7][0])) {
            btnTMinus.setEnabled(false);
        } else {
            btnTMinus.setEnabled(true);
        }
        if ("not".equals(skills[8][0])) {
            btnTPlus.setEnabled(true);
        } else {
            btnTPlus.setEnabled(false);
        }
    }

    private void separando(int intao, String daranha) {
        int charcountB = 0;
        for (int k = 0; k < 22; k++) {
            String botrray = "";
            while (!"#".equals(String.valueOf(daranha.charAt(charcountB)))) {
                botrray += daranha.charAt(charcountB);
                charcountB += 1;
            }
            skills[intao][k] = botrray;
            charcountB += 1;
        }
    }

    public String lerConteudoArquivo(File arquivo) throws IOException {
        StringBuilder retorno = new StringBuilder();
        BufferedReader conteudo = new BufferedReader(new FileReader(arquivo));
        while (conteudo.ready()) {
            retorno.append(conteudo.readLine()).append("\r\n");
        }
        return retorno.toString();
    }

    private void cbbAmudanca() {
        switch (cbbA.getSelectedItem().toString()) {
            case "Eclipse":
            case "Berserk":
            case "Blessing":
            case "Blizzard":
            case "Bulwark":
            case "Critical":
            case "Dispel":
            case "+DMG":
            case "-DMG":
            case "Dodge":
            case "Frostbite":
            case "Ice":
            case "Powder":
            case "Purify":
            case "Rage":
            case "Storm":
            case "Resilience":
            case "Riposte":
            case "Rune":
            case "Scarab":
            case "Shadow":
            case "Shield":
            case "Stench":
            case "Strength +":
            case "Strength -":
            case "Strength /":
            case "Strength =":
            case "Strength x":
            case "Terror":
            case "Thorn":
            case "Thunderstruck":
                checkAll.setVisible(true);
                checkOpp.setVisible(true);
                if (!checkAll.isSelected()) {
                    cbbAny.setVisible(false);
                }
                txtN.setVisible(false);
                txtN.setText("1");
                cbbType.setVisible(true);
                if (cbbType.getSelectedIndex() == 1 & cbbType.getSelectedIndex() == 5) {
                    checkType.setVisible(true);
                }
                if (cbbType.getSelectedIndex() == 2 & cbbType.getSelectedIndex() == 4) {
                    lblbuffdebuff.setVisible(true);
                    counterBuffDebuff.setVisible(true);
                }
                txtV.setVisible(true);
                lblAdd.setVisible(true);
                cbbAddDMG.setVisible(true);
                if (!"damaging".equals(eraoque[currentSkill]) && !"DejaPAing".equals(eraoque[currentSkill])) {
                    cbbAddDMG.setModel(new DefaultComboBoxModel<>(new String[]{"No Add Effect", "Dice", "Opp Dice", "VS", "Ally", "Opp", "Attacker", "Defender", "Bonus/Malus", "opp Bonus/Malus"}));
                }

                if (cbbAddDMG.getSelectedIndex() == 0) {
                    txtAddDMG.setVisible(false);
                    cbbOperation.setVisible(false);
                    cbbChange.setVisible(false);
                    lblCounter.setVisible(false);
                    counter.setVisible(false);

                    cbbAnotherAddDMG.setVisible(false);
                    txtAnotherAddDMG.setVisible(false);
                    cbbAnotherOperation.setVisible(false);
                } else {
                    mudandoOperation(cbbAddDMG.getSelectedItem().toString());
                }
                cbbDiceChange.setVisible(false);
                lblTo.setVisible(false);
                cbbDiceChanged.setVisible(false);
                lblShieldbash.setVisible(false);
                cbbShieldbash.setVisible(false);

                if (cbbRace.getSelectedIndex() != 4 && !"Symbiosis".equals(skills[0][7]) && !"Symbiosis".equals(skills[1][7]) && !"Symbiosis".equals(skills[2][7]) && !"Symbiosis".equals(skills[3][7]) && !"Symbiosis".equals(skills[4][7]) && !"Symbiosis".equals(skills[5][7]) && !"Symbiosis".equals(skills[6][7]) && !"Symbiosis".equals(skills[7][7]) && !"Symbiosis".equals(skills[8][7])) {
                    checkSymb.setVisible(false);
                    checkSymb.setSelected(false);
                }
                insp[currentSkill] = 99;
                lblWL.setVisible(false);
                rbtWinning.setVisible(false);
                rbtLosing.setVisible(false);

                eraoque[currentSkill] = "buffing";
                break;
            case "Inspire":
                checkAll.setVisible(false);
                checkAll.setSelected(false);
                checkOpp.setVisible(false);
                checkOpp.setSelected(false);
                cbbAny.setVisible(true);
                lblWL.setVisible(false);
                rbtWinning.setVisible(false);
                rbtLosing.setVisible(false);
                cbbType.setVisible(false);
                checkType.setVisible(false);
                lblbuffdebuff.setVisible(false);
                counterBuffDebuff.setVisible(false);
                txtN.setVisible(false);
                txtV.setVisible(false);
                lblAdd.setVisible(false);
                cbbAddDMG.setVisible(false);
                cbbAddDMG.setSelectedIndex(0);
                cbbDiceChange.setVisible(false);
                lblTo.setVisible(false);
                cbbDiceChanged.setVisible(false);
                lblShieldbash.setVisible(false);
                cbbShieldbash.setVisible(false);
                if (cbbRace.getSelectedIndex() != 4 && !"Symbiosis".equals(skills[0][7]) && !"Symbiosis".equals(skills[1][7]) && !"Symbiosis".equals(skills[2][7]) && !"Symbiosis".equals(skills[3][7]) && !"Symbiosis".equals(skills[4][7]) && !"Symbiosis".equals(skills[5][7]) && !"Symbiosis".equals(skills[6][7]) && !"Symbiosis".equals(skills[7][7]) && !"Symbiosis".equals(skills[8][7])) {
                    checkSymb.setVisible(false);
                    checkSymb.setSelected(false);
                }
                insp[currentSkill] = cbbAny.getSelectedIndex();
                eraoque[currentSkill] = "inspiring";
                break;
            case "Symbiosis":
                checkAll.setVisible(false);
                checkAll.setSelected(false);
                checkOpp.setVisible(false);
                checkOpp.setSelected(false);
                cbbAny.setVisible(true);
                cbbType.setVisible(false);
                lblbuffdebuff.setVisible(false);
                counterBuffDebuff.setVisible(false);
                checkType.setVisible(false);
                txtN.setVisible(false);
                txtV.setVisible(false);
                lblAdd.setVisible(false);
                cbbAddDMG.setVisible(false);
                cbbAddDMG.setSelectedIndex(0);
                cbbDiceChange.setVisible(false);
                lblTo.setVisible(false);
                lblWL.setVisible(false);
                rbtWinning.setVisible(false);
                rbtLosing.setVisible(false);
                cbbDiceChanged.setVisible(false);
                lblShieldbash.setVisible(false);
                cbbShieldbash.setVisible(false);
                checkSymb.setVisible(true);
                insp[currentSkill] = 99;
                eraoque[currentSkill] = "symbing";
                break;
            case "Deja Vu":
            case "Physical Attack":
                checkAll.setVisible(false);
                checkAll.setSelected(false);
                checkOpp.setVisible(false);
                checkOpp.setSelected(false);
                cbbAny.setVisible(false);
                txtN.setVisible(true);
                txtV.setVisible(false);
                cbbType.setVisible(false);
                lblbuffdebuff.setVisible(false);
                counterBuffDebuff.setVisible(false);
                checkType.setVisible(false);
                lblAdd.setVisible(true);
                cbbAddDMG.setVisible(true);
                lblWL.setVisible(false);
                rbtWinning.setVisible(false);
                rbtLosing.setVisible(false);
                if (!"damaging".equals(eraoque[currentSkill]) && !"buffing".equals(eraoque[currentSkill])) {
                    cbbAddDMG.setModel(new DefaultComboBoxModel<>(new String[]{"No Add Effect", "Dice", "Opp Dice", "VS", "Ally", "Opp", "Attacker", "Defender", "Bonus/Malus", "opp Bonus/Malus"}));
                }

                if (cbbAddDMG.getSelectedIndex() == 0) {
                    txtAddDMG.setVisible(false);
                    cbbOperation.setVisible(false);
                    cbbChange.setVisible(false);
                    lblCounter.setVisible(false);
                    counter.setVisible(false);

                    cbbAnotherAddDMG.setVisible(false);
                    txtAnotherAddDMG.setVisible(false);
                    cbbAnotherOperation.setVisible(false);
                } else {
                    mudandoOperation(cbbAddDMG.getSelectedItem().toString());
                }
                cbbDiceChange.setVisible(false);
                lblTo.setVisible(false);
                cbbDiceChanged.setVisible(false);

                lblShieldbash.setVisible(false);
                cbbShieldbash.setVisible(false);

                if (cbbRace.getSelectedIndex() != 4 && !"Symbiosis".equals(skills[0][7]) && !"Symbiosis".equals(skills[1][7]) && !"Symbiosis".equals(skills[2][7]) && !"Symbiosis".equals(skills[3][7]) && !"Symbiosis".equals(skills[4][7]) && !"Symbiosis".equals(skills[5][7]) && !"Symbiosis".equals(skills[6][7]) && !"Symbiosis".equals(skills[7][7]) && !"Symbiosis".equals(skills[8][7])) {
                    checkSymb.setVisible(false);
                    checkSymb.setSelected(false);
                }
                insp[currentSkill] = 99;

                eraoque[currentSkill] = "DejaPAing";
                break;
            case "Dice Change":
                checkAll.setVisible(false);
                checkAll.setSelected(false);
                checkOpp.setVisible(true);
                checkOpp.setSelected(false);
                cbbAny.setVisible(false);
                cbbType.setVisible(false);
                lblbuffdebuff.setVisible(false);
                counterBuffDebuff.setVisible(false);
                checkType.setVisible(false);
                txtN.setVisible(true);
                txtV.setVisible(false);
                lblAdd.setVisible(true);
                lblWL.setVisible(false);
                rbtWinning.setVisible(false);
                rbtLosing.setVisible(false);
                cbbAddDMG.setVisible(true);
                cbbAddDMG.setModel(new DefaultComboBoxModel<>(new String[]{"No Add Effect", "VS", "Attacker", "Defender"}));
                if (cbbAddDMG.getSelectedIndex() == 0) {
                    txtAddDMG.setVisible(false);
                    cbbOperation.setVisible(false);
                    cbbChange.setVisible(false);
                    lblCounter.setVisible(false);
                    counter.setVisible(false);
                }
                cbbAnotherAddDMG.setVisible(false);
                txtAnotherAddDMG.setVisible(false);
                cbbAnotherOperation.setVisible(false);
                cbbDiceChange.setVisible(true);
                lblTo.setVisible(true);
                cbbDiceChanged.setVisible(true);
                lblShieldbash.setVisible(false);
                cbbShieldbash.setVisible(false);
                if (cbbRace.getSelectedIndex() != 4 && !"Symbiosis".equals(skills[0][7]) && !"Symbiosis".equals(skills[1][7]) && !"Symbiosis".equals(skills[2][7]) && !"Symbiosis".equals(skills[3][7]) && !"Symbiosis".equals(skills[4][7]) && !"Symbiosis".equals(skills[5][7]) && !"Symbiosis".equals(skills[6][7]) && !"Symbiosis".equals(skills[7][7]) && !"Symbiosis".equals(skills[8][7])) {
                    checkSymb.setVisible(false);
                    checkSymb.setSelected(false);
                }
                insp[currentSkill] = 99;
                eraoque[currentSkill] = "dicing";
                break;
            default:
                checkAll.setVisible(false);
                checkAll.setSelected(false);
                checkOpp.setVisible(false);
                checkOpp.setSelected(false);
                cbbAny.setVisible(false);
                txtN.setVisible(true);
                cbbType.setVisible(true);
                txtV.setVisible(true);
                lblAdd.setVisible(true);
                cbbAddDMG.setVisible(true);
                if ("Avenge".equals(cbbA.getSelectedItem().toString()) || "Heroism".equals(cbbA.getSelectedItem().toString())) {
                    lblWL.setVisible(true);
                    rbtWinning.setVisible(true);
                    rbtLosing.setVisible(true);
                }
                if (!eraoque[currentSkill].equals("damaging")) {
                    if (cbbType.getSelectedIndex() == 1 & cbbType.getSelectedIndex() == 3) {
                        checkType.setVisible(true);
                    }
                    if (cbbType.getSelectedIndex() == 2 & cbbType.getSelectedIndex() == 4) {
                        lblbuffdebuff.setVisible(true);
                        counterBuffDebuff.setVisible(true);
                    }
                    if (!"buffing".equals(eraoque[currentSkill]) && !"DejaPAing".equals(eraoque[currentSkill])) {
                        cbbAddDMG.setModel(new DefaultComboBoxModel<>(new String[]{"No Add Effect", "Dice", "Opp Dice", "VS", "Ally", "Opp", "Attacker", "Defender", "Bonus/Malus", "opp Bonus/Malus"}));
                    }
                    if (cbbAddDMG.getSelectedIndex() == 0) {
                        txtAddDMG.setVisible(false);
                        cbbOperation.setVisible(false);
                        cbbChange.setVisible(false);
                        lblCounter.setVisible(false);
                        counter.setVisible(false);

                        cbbAnotherAddDMG.setVisible(false);
                        txtAnotherAddDMG.setVisible(false);
                        cbbAnotherOperation.setVisible(false);
                    } else {
                        mudandoOperation(cbbAddDMG.getSelectedItem().toString());
                    }
                    cbbDiceChange.setVisible(false);
                    lblTo.setVisible(false);
                    cbbDiceChanged.setVisible(false);
                    if ("Shield Bash".equals(cbbA.getSelectedItem().toString())) {
                        lblShieldbash.setVisible(true);
                        cbbShieldbash.setVisible(true);
                    } else {
                        lblShieldbash.setVisible(false);
                        cbbShieldbash.setVisible(false);
                    }
                    if (cbbRace.getSelectedIndex() != 4 && !"Symbiosis".equals(skills[0][7]) && !"Symbiosis".equals(skills[1][7]) && !"Symbiosis".equals(skills[2][7]) && !"Symbiosis".equals(skills[3][7]) && !"Symbiosis".equals(skills[4][7]) && !"Symbiosis".equals(skills[5][7]) && !"Symbiosis".equals(skills[6][7]) && !"Symbiosis".equals(skills[7][7]) && !"Symbiosis".equals(skills[8][7])) {
                        checkSymb.setVisible(false);
                        checkSymb.setSelected(false);
                    }
                    insp[currentSkill] = 99;
                } else {
                    if (cbbAddDMG.getItemCount() < 6) {
                        cbbAddDMG.setModel(new DefaultComboBoxModel<>(new String[]{"No Add Effect", "Dice", "Opp Dice", "VS", "Ally", "Opp", "Attacker", "Defender", "Bonus/Malus", "opp Bonus/Malus"}));
                    }
                    if ("Shield Bash".equals(cbbA.getSelectedItem().toString())) {
                        lblShieldbash.setVisible(true);
                        cbbShieldbash.setVisible(true);
                    } else {
                        lblShieldbash.setVisible(false);
                        cbbShieldbash.setVisible(false);
                    }
                }
                eraoque[currentSkill] = "damaging";
                break;
        }
    }

    private void resetingValues(int allc) {
        minimando();

//        dispelList.clear();
        textbot[0] = "";
        textbot[1] = "";
        textbot[2] = "";
        textbot[3] = "";
        textbot[4] = "";
        textmid[0] = "";
        textmid[1] = "";
        textmid[2] = "";
        textmid[3] = "";
        textmid[4] = "";
        texttop[0] = "";
        texttop[1] = "";
        texttop[2] = "";
        texttop[3] = "";
        texttop[4] = "";

        haveinspire = 0;

        setmade = "";

        if (checkExtra.isSelected()) {
            rollcalc = new int[7];
            if (allc == 999) {
                rollcalc[0] = D1.getSelectedIndex();
                rollcalc[1] = D2.getSelectedIndex();
                rollcalc[2] = D3.getSelectedIndex();
                rollcalc[3] = D4.getSelectedIndex();
                rollcalc[4] = D5.getSelectedIndex();
                rollcalc[5] = D6.getSelectedIndex();
                rollcalc[6] = D7.getSelectedIndex();
            } else {
                if (!checkTrans.isSelected()) {
                    rollcalc[0] = all7throlls[allc][0];
                    rollcalc[1] = all7throlls[allc][1];
                    rollcalc[2] = all7throlls[allc][2];
                    rollcalc[3] = all7throlls[allc][3];
                    rollcalc[4] = all7throlls[allc][4];
                    rollcalc[5] = all7throlls[allc][5];
                    rollcalc[6] = all7throlls[allc][6];
                } else {
                    rollcalc[0] = all7thtransrolls[allc][0];
                    rollcalc[1] = all7thtransrolls[allc][1];
                    rollcalc[2] = all7thtransrolls[allc][2];
                    rollcalc[3] = all7thtransrolls[allc][3];
                    rollcalc[4] = all7thtransrolls[allc][4];
                    rollcalc[5] = all7thtransrolls[allc][5];
                    rollcalc[6] = all7thtransrolls[allc][6];
                }
            }
            used = new int[7];

            used[0] = 0;
            used[1] = 0;
            used[2] = 0;
            used[3] = 0;
            used[4] = 0;
            used[5] = 0;
            used[6] = 0;
        } else {
            rollcalc = new int[6];
            if (allc == 999) {
                rollcalc[0] = D1.getSelectedIndex();
                rollcalc[1] = D2.getSelectedIndex();
                rollcalc[2] = D3.getSelectedIndex();
                rollcalc[3] = D4.getSelectedIndex();
                rollcalc[4] = D5.getSelectedIndex();
                rollcalc[5] = D6.getSelectedIndex();
            } else {
                if (!checkTrans.isSelected()) {
                    rollcalc[0] = allrolls[allc][0];
                    rollcalc[1] = allrolls[allc][1];
                    rollcalc[2] = allrolls[allc][2];
                    rollcalc[3] = allrolls[allc][3];
                    rollcalc[4] = allrolls[allc][4];
                    rollcalc[5] = allrolls[allc][5];
                } else {
                    rollcalc[0] = alltransrolls[allc][0];
                    rollcalc[1] = alltransrolls[allc][1];
                    rollcalc[2] = alltransrolls[allc][2];
                    rollcalc[3] = alltransrolls[allc][3];
                    rollcalc[4] = alltransrolls[allc][4];
                    rollcalc[5] = alltransrolls[allc][5];
                }
            }

            used = new int[6];

            used[0] = 0;
            used[1] = 0;
            used[2] = 0;
            used[3] = 0;
            used[4] = 0;
            used[5] = 0;

        }

        total = 0;
        damage = 0;
        mmcounter = 0;
        ptcounter = 0;
        totalscarab = 0;
        sbcounter = 0;
        oppminusstr = 0;
        djcounter = 0;
        oldblizzard = 0;
        prcounter = 0;
        blizcon = 0;
        heal = 0;

        berserk = 0;
        blessing = 0;
        blizzard = 0;
        bulwark = 0;
        critical = 0;
        moredamage = 0;
        dodge = 0;
        eclipse = 0;
        frostbite = 0;
        ice = 0;
        malus = 0;
        powder = 0;
        str = Integer.parseInt(txtStr.getText());
        resilience = 0;
        rage = 0;
        scarab = 0;
        runes = 0;
        riposte = 0;
        shield = 0;
        stench = 0;
        storm = 0;
        terror = 0;
        thorn = 0;
        thunderstruck = 0;
        shadow = 0;
        oppberserk = 0;
        oppblessing = 0;
        oppblizzard = 0;
        oppbulwark = 0;
        oppcritical = 0;
        oppmoredamage = 0;
        oppdodge = 0;
        oppeclipse = 0;
        oppfrostbite = 0;
        oppice = 0;
        oppmalus = 0;
        oppPowder = 0;
        oppstr = Integer.parseInt(txtOppStr.getText());
        oppresilience = 0;
        opprage = 0;
        oppstorm = 0;
        oppscarab = 0;
        opprunes = 0;
        oppriposte = 0;
        oppshield = 0;
        oppstench = 0;
        oppterror = 0;
        oppthorn = 0;
        oppthunderstruck = 0;
        oppshadow = 0;
        oppequalcounter = -1;

        for (int i = 0; i < 9; i++) {
            all[i] = 0;
        }
        for (int i = 0; i < 9; i++) {
            fixeall[i] = 0;
        }
        for (int i = 0; i < 9; i++) {
            moreif[i] = 0;
        }
        for (int i = 0; i < 9; i++) {
            fixemoreif[i] = 0;
        }

        updateValues();

        dicesbot.clear();
        dicesmid.clear();
        dicestop.clear();

        actbot = 0;
        actmid = 0;
        acttop = 0;

        utils = new Roll();

        bot = new int[5];
        mid = new int[5];
        top = new int[5];

        nlblsB[0] = BD1.getSelectedIndex();
        nlblsB[1] = BD2.getSelectedIndex();
        nlblsB[2] = BD3.getSelectedIndex();
        nlblsB[3] = BD4.getSelectedIndex();
        nlblsB[4] = BD5.getSelectedIndex();

        nlblsM[0] = MD1.getSelectedIndex();
        nlblsM[1] = MD2.getSelectedIndex();
        nlblsM[2] = MD3.getSelectedIndex();
        nlblsM[3] = MD4.getSelectedIndex();
        nlblsM[4] = MD5.getSelectedIndex();

        nlblsT[0] = TD1.getSelectedIndex();
        nlblsT[1] = TD2.getSelectedIndex();
        nlblsT[2] = TD3.getSelectedIndex();
        nlblsT[3] = TD4.getSelectedIndex();
        nlblsT[4] = TD5.getSelectedIndex();

        alreadyConsumed[0] = 0;
        alreadyConsumed[1] = 0;
        alreadyConsumed[2] = 0;
        alreadyConsumed[3] = 0;
        alreadyConsumed[4] = 0;
        alreadyConsumed[5] = 0;
        alreadyConsumed[6] = 0;
        alreadyConsumed[7] = 0;
        alreadyConsumed[8] = 0;

        shieldconsumed = 0;

        details = "";
        rollsDices = "";

        oppdvdcounter = 1;

        dispelcounter = 0;
    }

    public void updateValues() {
        if (!checkSymb.isSelected()) {
            for (int i = 0; i < 9; i++) {
                fixeall[i] = Integer.parseInt(skills[i][8]);
                vallist[i] = fullValue(i);
            }
        } else {
            for (int i = 0; i < 9; i++) {
                fixeall[i] = Integer.parseInt(skills[i][8]) * 2;
                vallist[i] = fullValue(i) * 2;
            }
        }
    }

    public int fullValue(int x) {
        String cbbAdd = skills[x][12];
        String cbbOpera = skills[x][14];
        String cbbChangeable = skills[x][15];
        int value = Integer.parseInt(skills[x][8]);
        int addvalue = Integer.parseInt(skills[x][13]);

        if ("No Add Effect".equals(cbbAdd)) {
            return value;
        } else {
            int addCounter = 0;
            switch (cbbAdd) {
                case "Dice":
                    switch (cbbChangeable) {
                        case "Red":
                            for (int i = 0; i < rollcalc.length; i++) {
                                if (rollcalc[i] == 0) {
                                    addCounter += 1;
                                }
                            }
                            break;
                        case "Blue":
                            for (int i = 0; i < rollcalc.length; i++) {
                                if (rollcalc[i] == 1) {
                                    addCounter += 1;
                                }
                            }
                            break;
                        case "Yellow":
                            for (int i = 0; i < rollcalc.length; i++) {
                                if (rollcalc[i] == 2) {
                                    addCounter += 1;
                                }
                            }
                            break;
                        case "Sword":
                            for (int i = 0; i < rollcalc.length; i++) {
                                if (rollcalc[i] == 3) {
                                    addCounter += 1;
                                }
                            }
                            break;
                    }
                    switch (cbbOpera) {
                        case "[VAL] x":
                            if ("1".equals(skills[x][17]) || "2".equals(skills[x][17]) || "3".equals(skills[x][17])) {
                                value = againFullValue(x, skills[x][17], skills[x][18], value);
                            }
                            fixeall[x] = value * addCounter;
                            return value * addCounter;
                        case "+[VAL] per":
                            fixeall[x] = value + (addvalue * addCounter);
                            return value + (addvalue * addCounter);
                        case "-[VAL] per":
                            fixeall[x] = value - (addvalue * addCounter);
                            return value - (addvalue * addCounter);
                        default:
                            fixeall[x] = value;
                            return value;
                    }
                case "Opp Dice":
                    addCounter = Integer.parseInt(skills[x][16]);
                    switch (cbbOpera) {
                        case "[VAL] x":
                            if ("1".equals(skills[x][17]) || "2".equals(skills[x][17]) || "3".equals(skills[x][17])) {
                                value = againFullValue(x, skills[x][17], skills[x][18], value);
                            }
                            fixeall[x] = value * addCounter;
                            return value * addCounter;
                        case "+[VAL] per":
                            fixeall[x] = value + (addvalue * addCounter);
                            return value + (addvalue * addCounter);
                        case "-[VAL] per":
                            fixeall[x] = value - (addvalue * addCounter);
                            return value - (addvalue * addCounter);
                        default:
                            fixeall[x] = value;
                            return value;
                    }
                case "VS":
                    switch (cbbOpera) {
                        case "[VAL] VS":
                            if (cbbChangeable.equals(opprace) || cbbChangeable.equals(oppguild) || cbbChangeable.equals(oppclas)) {
                                fixeall[x] = Integer.parseInt(skills[x][13]);
                                return Integer.parseInt(skills[x][13]);
                            } else {
                                fixeall[x] = againFullValue(x, skills[x][17], skills[x][13], value);
                                return againFullValue(x, skills[x][17], skills[x][13], value);
                            }
                        case "only VS":
                            fixeall[x] = value;
                            return value;
                        default:
                            fixeall[x] = value;
                            return value;
                    }
                case "Ally":
                    addCounter = Integer.parseInt(skills[x][16]);
                    switch (cbbOpera) {
                        case "[VAL] x":
                            if ("1".equals(skills[x][17]) || "2".equals(skills[x][17]) || "3".equals(skills[x][17])) {
                                value = againFullValue(x, skills[x][17], skills[x][18], value);
                            }
                            fixeall[x] = value * addCounter;
                            return value * addCounter;
                        case "+[VAL] per":
                            fixeall[x] = value + (addvalue * addCounter);
                            return value + (addvalue * addCounter);
                        case "-[VAL] per":
                            fixeall[x] = value - (addvalue * addCounter);
                            return value - (addvalue * addCounter);
                        default:
                            fixeall[x] = value;
                            return value;
                    }
                case "Opp":
                    addCounter = Integer.parseInt(skills[x][16]);
                    switch (cbbOpera) {
                        case "[VAL] x":
                            if ("1".equals(skills[x][17]) || "2".equals(skills[x][17]) || "3".equals(skills[x][17])) {
                                value = againFullValue(x, skills[x][17], skills[x][18], value);
                            }
                            fixeall[x] = value * addCounter;
                            return value * addCounter;
                        case "+[VAL] per":
                            fixeall[x] = value + (addvalue * addCounter);
                            return value + (addvalue * addCounter);
                        case "-[VAL] per":
                            fixeall[x] = value - (addvalue * addCounter);
                            return value - (addvalue * addCounter);
                        default:
                            fixeall[x] = value;
                            return value;
                    }
                case "Attacker":
                    switch (cbbOpera) {
                        case "[VAL] if atk":
                            if (rbtAtk.isSelected()) {
                                fixeall[x] = Integer.parseInt(skills[x][13]);
                                return Integer.parseInt(skills[x][13]);
                            } else {
                                fixeall[x] = value;
                                return value;
                            }
                        case "only if atk":
                            if (rbtAtk.isSelected()) {
                                fixeall[x] = value;
                                return value;
                            }
                        default:
                            fixeall[x] = value;
                            return value;
                    }
                case "Defender":
                    switch (cbbOpera) {
                        case "[VAL] if dfd":
                            if (rbtDfd.isSelected()) {
                                fixeall[x] = Integer.parseInt(skills[x][13]);
                                return Integer.parseInt(skills[x][13]);
                            } else {
                                fixeall[x] = value;
                                return value;
                            }
                        case "only if dfd":
                            if (rbtDfd.isSelected()) {
                                fixeall[x] = value;
                                return value;
                            }
                        default:
                            fixeall[x] = value;
                            return value;
                    }
                case "Bonus/Malus":
                    switch (cbbChangeable) {
                        case "Berserk":
                            addCounter = berserk;
                            break;
                        case "Blessing":
                            addCounter = blessing;
                            break;
                        case "Blizzard":
                            addCounter = blizzard;
                            break;
                        case "Bulwark":
                            addCounter = bulwark;
                            break;
                        case "Critical":
                            addCounter = critical;
                            break;
                        case "+DMG":
                            addCounter = moredamage;
                            break;
                        case "-DMG":
                            addCounter = malus;
                            break;
                        case "Dodge":
                            addCounter = dodge;
                            break;
                        case "Eclipse":
                            addCounter = eclipse;
                            break;
                        case "Frostbite":
                            addCounter = frostbite;
                            break;
                        case "Ice":
                            addCounter = ice;
                            break;
                        case "Powder":
                            addCounter = powder;
                            break;
                        case "Rage":
                            addCounter = rage;
                            break;
                        case "Resilience":
                            addCounter = resilience;
                            break;
                        case "Riposte":
                            addCounter = riposte;
                            break;
                        case "Rune":
                            addCounter = runes;
                            break;
                        case "Scarab":
                            addCounter = scarab;
                            break;
                        case "Shield":
                            addCounter = shield;
                            break;
                        case "Stench":
                            addCounter = stench;
                            break;
                        case "Storm":
                            addCounter = storm;
                            break;
                        case "Strength":
                            addCounter = str;
                            break;
                        case "Terror":
                            addCounter = terror;
                            break;
                        case "Thorn":
                            addCounter = thorn;
                            break;
                        case "Thunderstruck":
                            addCounter = thunderstruck;
                            break;
                    }
                    switch (cbbOpera) {
                        case "[VAL] x":
                            if ("1".equals(skills[x][17]) || "2".equals(skills[x][17]) || "3".equals(skills[x][17])) {
                                value = againFullValue(x, skills[x][17], skills[x][18], value);
                            }
                            fixeall[x] = value * addCounter;
                            return value * addCounter;
                        case "[VAL] +":
                            if ("1".equals(skills[x][17]) || "2".equals(skills[x][17]) || "3".equals(skills[x][17])) {
                                value = againFullValue(x, skills[x][17], skills[x][18], value);
                            }
                            fixeall[x] = value + addCounter;
                            return value + addCounter;
                        case "[VAL] -":
                            if ("1".equals(skills[x][17]) || "2".equals(skills[x][17]) || "3".equals(skills[x][17])) {
                                value = againFullValue(x, skills[x][17], skills[x][18], value);
                            }
                            fixeall[x] = value - addCounter;
                            return value - addCounter;
                        case "/ [VAL]":
                            if ("1".equals(skills[x][17]) || "2".equals(skills[x][17]) || "3".equals(skills[x][17])) {
                                value = againFullValue(x, skills[x][17], skills[x][18], value);
                            }
                            fixeall[x] = addCounter / value;
                            return addCounter / value;
                        case "+[VAL] per":
                            fixeall[x] = value + (addvalue * addCounter);
                            return value + (addvalue * addCounter);
                        case "-[VAL] per":
                            fixeall[x] = value - (addvalue * addCounter);
                            return value - (addvalue * addCounter);
                        default:
                            fixeall[x] = value;
                            return value;
                    }
                case "opp Bonus/Malus":
                    switch (cbbChangeable) {
                        case "Berserk":
                            addCounter = oppberserk;
                            break;
                        case "Blessing":
                            addCounter = oppblessing;
                            break;
                        case "Blizzard":
                            addCounter = oppblizzard;
                            break;
                        case "Bulwark":
                            addCounter = oppbulwark;
                            break;
                        case "Critical":
                            addCounter = oppcritical;
                            break;
                        case "+DMG":
                            addCounter = oppmoredamage;
                            break;
                        case "-DMG":
                            addCounter = oppmalus;
                            break;
                        case "Dodge":
                            addCounter = oppdodge;
                            break;
                        case "Eclipse":
                            addCounter = oppeclipse;
                            break;
                        case "Frostbite":
                            addCounter = oppfrostbite;
                            break;
                        case "Ice":
                            addCounter = oppice;
                            break;
                        case "Powder":
                            addCounter = oppPowder;
                            break;
                        case "Rage":
                            addCounter = opprage;
                            break;
                        case "Storm":
                            addCounter = oppstorm;
                            break;
                        case "Resilience":
                            addCounter = oppresilience;
                            break;
                        case "Riposte":
                            addCounter = oppriposte;
                            break;
                        case "Rune":
                            addCounter = opprunes;
                            break;
                        case "Scarab":
                            addCounter = oppscarab;
                            break;
                        case "Shield":
                            addCounter = oppshield;
                            break;
                        case "Stench":
                            addCounter = oppstench;
                            break;
                        case "Strength":
                            addCounter = oppstr;
                            break;
                        case "Terror":
                            addCounter = oppterror;
                            break;
                        case "Thorn":
                            addCounter = oppthorn;
                            break;
                        case "Thunderstruck":
                            addCounter = oppthunderstruck;
                            break;
                    }
                    switch (cbbOpera) {
                        case "[VAL] x":
                            if ("1".equals(skills[x][17]) || "2".equals(skills[x][17]) || "3".equals(skills[x][17])) {
                                value = againFullValue(x, skills[x][17], skills[x][18], value);
                            }
                            fixeall[x] = value * addCounter;
                            return value * addCounter;
                        case "[VAL] +":
                            if ("1".equals(skills[x][17]) || "2".equals(skills[x][17]) || "3".equals(skills[x][17])) {
                                value = againFullValue(x, skills[x][18], skills[x][18], value);
                            }
                            fixeall[x] = value + addCounter;
                            return value + addCounter;
                        case "[VAL] -":
                            if ("1".equals(skills[x][17]) || "2".equals(skills[x][17]) || "3".equals(skills[x][17])) {
                                value = againFullValue(x, skills[x][17], skills[x][18], value);
                            }
                            fixeall[x] = value - addCounter;
                            return value - addCounter;
                        case "/ [VAL]":
                            if ("1".equals(skills[x][17]) || "2".equals(skills[x][17]) || "3".equals(skills[x][17])) {
                                value = againFullValue(x, skills[x][17], skills[x][18], value);
                            }
                            fixeall[x] = addCounter / value;
                            return addCounter / value;
                        case "+[VAL] per":
                            fixeall[x] = value + (addvalue * addCounter);
                            return value + (addvalue * addCounter);
                        case "-[VAL] per":
                            fixeall[x] = value - (addvalue * addCounter);
                            return value - (addvalue * addCounter);
                        default:
                            fixeall[x] = value;
                            return value;
                    }
                case "if":
                    fixemoreif[x] = addvalue;
                    fixeall[x] = value;
                    if (cbbChangeable.equals(race) || cbbChangeable.equals(guild) || cbbChangeable.equals(clas)) {
                        return fixemoreif[x];
                    } else {
                        return value;
                    }
                default:
                    return value;
            }
        }
    }

    public int againFullValue(int x, String oque, String quantopodevirar, int quantoe) {
        String vs = "";
        switch (skills[x][19]) {
            case "0":
                vs = "Ava";
                break;
            case "1":
                vs = "DN";
                break;
            case "2":
                vs = "Kot";
                break;
            case "3":
                vs = "Merc";
                break;
            case "4":
                vs = "Neh";
                break;
            case "5":
                vs = "Noz";
                break;
            case "6":
                vs = "Pir";
                break;
            case "7":
                vs = "RL";
                break;
            case "8":
                vs = "SH";
                break;
            case "9":
                vs = "SL";
                break;
            case "10":
                vs = "Temp";
                break;
            case "11":
                vs = "WT";
                break;
            case "12":
                vs = "Zil";
                break;
            case "13":
                vs = "Bard";
                break;
            case "14":
                vs = "Berserker";
                break;
            case "15":
                vs = "Colossus";
                break;
            case "16":
                vs = "Craftsman";
                break;
            case "17":
                vs = "Mage";
                break;
            case "18":
                vs = "Marauder";
                break;
            case "19":
                vs = "Priest";
                break;
            case "20":
                vs = "UnkClass";
                break;
            case "21":
                vs = "Warrior";
                break;
            case "22":
                vs = "Beast";
                break;
            case "23":
                vs = "Dais";
                break;
            case "24":
                vs = "Demon";
                break;
            case "25":
                vs = "Dragon";
                break;
            case "26":
                vs = "Elfine";
                break;
            case "27":
                vs = "Eltarite";
                break;
            case "28":
                vs = "Golem";
                break;
            case "29":
                vs = "Guem";
                break;
            case "30":
                vs = "Homchai";
                break;
            case "31":
                vs = "Human";
                break;
            case "32":
                vs = "Ice Elf";
                break;
            case "33":
                vs = "Solarian";
                break;
            case "34":
                vs = "Undead";
                break;
            case "35":
                vs = "UnkRace";
                break;
        }
        switch (oque) {
            case "1":
                if (vs.equals(opprace) || vs.equals(oppguild) || vs.equals(oppclas)) {
                    return Integer.parseInt(quantopodevirar);
                } else {
                    return quantoe;
                }
            case "2":
                if (rbtAtk.isSelected()) {
                    return Integer.parseInt(quantopodevirar);
                } else {
                    return quantoe;
                }
            case "3":
                if (rbtDfd.isSelected()) {
                    return Integer.parseInt(quantopodevirar);
                } else {
                    return quantoe;
                }
            default:
                return quantoe;
        }
    }

    public int typibility(int x, int type, int value, int bubuff) {
        //0-normal  1-brave 2-icy   3-noble 4-runic 5-sacred 6-timed 7-freaky
        switch (type) {
            case 0:
                if ("Smite".equals(skills[x][7])) {
                    return value * oppstr;
                } else {
                    return value;
                }
            case 1:
                if ("t".equals(skills[x][5])) {
                    if ("Smite".equals(skills[x][7])) {
                        return value * oppstr + (value * oppstr / 2);
                    } else {
                        return value + (value / 2);
                    }
                } else {
                    return value;
                }
            case 2:
                int pttc = 0;
        if (oppeclipse > 0) {
            pttc++;
        }
        if (oppmalus > 0) {
            pttc++;
        }
        if (oppice > 0) {
            pttc++;
        }
        if (oppfrostbite > 0) {
            pttc++;
        }
        if (oppthunderstruck > 0) {
            pttc++;
        }
        if (oppterror > 0) {
            pttc++;
        }
        if (oppPowder > 0) {
            pttc++;
        }
                if ("Smite".equals(skills[x][7])) {
                    return value * oppstr + (value * oppstr / 2 * pttc);
                } else {
                    return value + (value / 2 * pttc);
                }
            case 3:
                double iced;
                if ("Smite".equals(skills[x][7])) {
                    iced = value * oppstr + (value * oppstr / 10 * oppice);
                } else {
                    iced = value + (value / 10 * oppice);
                }
                freeze(1);
                return (int) iced;
            case 4:
                int pttcc = 0;
        if (eclipse > 0) {
            pttcc++;
        }
        if (malus > 0) {
            pttcc++;
        }
        if (ice > 0) {
            pttcc++;
        }
        if (frostbite > 0) {
            pttcc++;
        }
        if (thunderstruck > 0) {
            pttcc++;
        }
        if (terror > 0) {
            pttcc++;
        }
        if (powder > 0) {
            pttcc++;
        }
                if ("Smite".equals(skills[x][7])) {
                    return value * oppstr + (value * oppstr / 2 * pttcc);
                } else {
                    return value + (value / 2 * pttcc);
                }
            case 5:
                if ("t".equals(skills[x][5])) {
                    if ("Smite".equals(skills[x][7])) {
                        return value * oppstr + (value * oppstr / 2);
                    } else {
                        return value + (value / 2);
                    }
                } else {
                    return value;
                }
            case 6:
                double runic;
                if ("Smite".equals(skills[x][7])) {
                    runic = value * oppstr + (value * oppstr / 2 * runes);
                } else {
                    runic = value + (value / 2 * runes);
                }
                runes = 0;
                return (int) runic;
            case 7:
                if (powder > 0 || thunderstruck > 0 || malus > 0 || terror > 0 || eclipse > 0 || ice > 0 || fatigue > 0 || pierce > 0) {
                    if ("Smite".equals(skills[x][7])) {
                        value = (value * oppstr) + ((value * oppstr) / 2);
                    } else {
                        value *= 1.5;
                    }
                }
                return value;
            case 8:
                if ("Smite".equals(skills[x][7])) {
                    value = (value * oppstr) + ((value * oppstr) / 4 * ((int) counter1.getValue() - 1));
                } else {
                    value = value + (value / 4 * ((int) counter1.getValue() - 1));
                }
                return (int) value;
            case 9:
                double freaky;
                if ("Smite".equals(skills[x][7])) {
                    freaky = value * oppstr + (value * oppstr * 3 / 10 * oppshadow);
                } else {
                    freaky = value + (value * 3 / 10 * oppshadow);
                }
                oppshadow = 0;
                return (int) freaky;
            default:
                return value;
        }
    }

    public void queroReusar() {
        used[0] = 0;
        used[1] = 0;
        used[2] = 0;
        used[3] = 0;
        used[4] = 0;
        used[5] = 0;
        if (checkExtra.isSelected()) {
            used[6] = 0;
        }
    }

    public void saveAbilityCosts() {
        for (int i = 0; i < 5; i++) {
            if (nlblsB[i] != 0) {
                dicesbot.add(nlblsB[i] - 1);
            }
            if (nlblsM[i] != 0) {
                dicesmid.add(nlblsM[i] - 1);
            }
            if (nlblsT[i] != 0) {
                dicestop.add(nlblsT[i] - 1);
            }
        }
    }

    public void howManyTimesBot() {
        utils = new Roll();
        for (int i = 0; i < rollcalc.length; i++) {
            if (rollcalc[i] == 0 & used[i] == 0) {
                utils.add(0);
            }
            if (rollcalc[i] == 1 & used[i] == 0) {
                utils.add(1);
            }
            if (rollcalc[i] == 2 & used[i] == 0) {
                utils.add(2);
            }
            if (rollcalc[i] == 3 & used[i] == 0) {
                utils.add(3);
            }
        }
        int red = 0;
        int blue = 0;
        int yellow = 0;
        int grey = 0;
        for (int k = 0; k < dicesbot.size(); k++) {
            if (dicesbot.get(k) == 0) {
                red += 1;
                textbot[k] = "R";
            }
            if (dicesbot.get(k) == 1) {
                blue += 1;
                textbot[k] = "B";
            }
            if (dicesbot.get(k) == 2) {
                yellow += 1;
                textbot[k] = "Y";
            }
            if (dicesbot.get(k) == 3) {
                grey += 1;
                textbot[k] = "S";
            }
        }

        if (dicesbot.isEmpty()) {
            actbot = 1;
        } else {
            ArrayList<Roll.Abidados> listona = new ArrayList<>();
            if (red > 0) {
                Roll.Abidados dr = utils.newAbidados(0, red);
                listona.add(dr);
            }
            if (blue > 0) {
                Roll.Abidados db = utils.newAbidados(1, blue);
                listona.add(db);
            }
            if (yellow > 0) {
                Roll.Abidados dy = utils.newAbidados(2, yellow);
                listona.add(dy);
            }
            if (grey > 0) {
                Roll.Abidados ds = utils.newAbidados(3, grey);
                listona.add(ds);
            }

            if (listona.size() == 1) {
                actbot = utils.getTimes(new Roll.Abidados[]{listona.get(0)});
            }
            if (listona.size() == 2) {
                actbot = utils.getTimes(new Roll.Abidados[]{listona.get(0), listona.get(1)});
            }
            if (listona.size() == 3) {
                actbot = utils.getTimes(new Roll.Abidados[]{listona.get(0), listona.get(1), listona.get(2)});
            }
            if (listona.size() == 4) {
                actbot = utils.getTimes(new Roll.Abidados[]{listona.get(0), listona.get(1), listona.get(2), listona.get(3)});
            }
        }
    }

    public void howManyTimesMid() {
        utils = new Roll();
        for (int i = 0; i < rollcalc.length; i++) {
            if (rollcalc[i] == 0 & used[i] == 0) {
                utils.add(0);
            }
            if (rollcalc[i] == 1 & used[i] == 0) {
                utils.add(1);
            }
            if (rollcalc[i] == 2 & used[i] == 0) {
                utils.add(2);
            }
            if (rollcalc[i] == 3 & used[i] == 0) {
                utils.add(3);
            }
        }
        int red = 0;
        int blue = 0;
        int yellow = 0;
        int grey = 0;
        for (int k = 0; k < dicesmid.size(); k++) {
            if (dicesmid.get(k) == 0) {
                red += 1;
                textmid[k] = "R";
            }
            if (dicesmid.get(k) == 1) {
                blue += 1;
                textmid[k] = "B";
            }
            if (dicesmid.get(k) == 2) {
                yellow += 1;
                textmid[k] = "Y";
            }
            if (dicesmid.get(k) == 3) {
                grey += 1;
                textmid[k] = "S";
            }
        }

        if (dicesmid.isEmpty()) {
            actmid = 1;
        } else {
            ArrayList<Roll.Abidados> listona = new ArrayList<>();
            if (red > 0) {
                Roll.Abidados dr = utils.newAbidados(0, red);
                listona.add(dr);
            }
            if (blue > 0) {
                Roll.Abidados db = utils.newAbidados(1, blue);
                listona.add(db);
            }
            if (yellow > 0) {
                Roll.Abidados dy = utils.newAbidados(2, yellow);
                listona.add(dy);
            }
            if (grey > 0) {
                Roll.Abidados ds = utils.newAbidados(3, grey);
                listona.add(ds);
            }

            if (listona.size() == 1) {
                actmid = utils.getTimes(new Roll.Abidados[]{listona.get(0)});
            }
            if (listona.size() == 2) {
                actmid = utils.getTimes(new Roll.Abidados[]{listona.get(0), listona.get(1)});
            }
            if (listona.size() == 3) {
                actmid = utils.getTimes(new Roll.Abidados[]{listona.get(0), listona.get(1), listona.get(2)});
            }
            if (listona.size() == 4) {
                actmid = utils.getTimes(new Roll.Abidados[]{listona.get(0), listona.get(1), listona.get(2), listona.get(3)});
            }
        }
    }

    private void howManyTimesTop() {
        utils = new Roll();
        for (int i = 0; i < rollcalc.length; i++) {
            if (rollcalc[i] == 0 & used[i] == 0) {
                utils.add(0);
            }
            if (rollcalc[i] == 1 & used[i] == 0) {
                utils.add(1);
            }
            if (rollcalc[i] == 2 & used[i] == 0) {
                utils.add(2);
            }
            if (rollcalc[i] == 3 & used[i] == 0) {
                utils.add(3);
            }
        }
        int red = 0;
        int blue = 0;
        int yellow = 0;
        int grey = 0;
        for (int k = 0; k < dicestop.size(); k++) {
            if (dicestop.get(k) == 0) {
                red += 1;
                texttop[k] = "R";
            }
            if (dicestop.get(k) == 1) {
                blue += 1;
                texttop[k] = "B";
            }
            if (dicestop.get(k) == 2) {
                yellow += 1;
                texttop[k] = "Y";
            }
            if (dicestop.get(k) == 3) {
                grey += 1;
                texttop[k] = "S";
            }
        }

        if (dicestop.isEmpty()) {
            acttop = 1;
        } else {
            ArrayList<Roll.Abidados> listona = new ArrayList<>();
            if (red > 0) {
                Roll.Abidados dr = utils.newAbidados(0, red);
                listona.add(dr);
            }
            if (blue > 0) {
                Roll.Abidados db = utils.newAbidados(1, blue);
                listona.add(db);
            }
            if (yellow > 0) {
                Roll.Abidados dy = utils.newAbidados(2, yellow);
                listona.add(dy);
            }
            if (grey > 0) {
                Roll.Abidados ds = utils.newAbidados(3, grey);
                listona.add(ds);
            }

            if (listona.size() == 1) {
                acttop = utils.getTimes(new Roll.Abidados[]{listona.get(0)});
            }
            if (listona.size() == 2) {
                acttop = utils.getTimes(new Roll.Abidados[]{listona.get(0), listona.get(1)});
            }
            if (listona.size() == 3) {
                acttop = utils.getTimes(new Roll.Abidados[]{listona.get(0), listona.get(1), listona.get(2)});
            }
            if (listona.size() == 4) {
                acttop = utils.getTimes(new Roll.Abidados[]{listona.get(0), listona.get(1), listona.get(2), listona.get(3)});
            }
        }
    }

    private void usandoDados(int ability) {
        switch (ability) {
            case 1:
                tresdados = dicesbot;
                break;
            case 2:
                tresdados = dicesmid;
                break;
            case 3:
                tresdados = dicestop;
                break;
        }
        for (int i = 0; i < tresdados.size(); i++) {
            for (int j = 0; j < rollcalc.length; j++) {
                if (rollcalc[j] == tresdados.get(i) && used[j] != 1) {
                    used[j] = 1;
                    j = 99;
                }
            }
        }
    }

    private void orderingDices() {
        int aux;
        for (int x = 0; x < rollcalc.length; x++) {
            for (int y = x + 1; y < rollcalc.length; y++) {
                if ((rollcalc[y] == 0 && (rollcalc[x] == 1 || rollcalc[x] == 2 || rollcalc[x] == 3)) || (rollcalc[y] == 1 && (rollcalc[x] == 2 || rollcalc[x] == 3)) || (rollcalc[y] == 2 && rollcalc[x] == 3)) {
                    aux = rollcalc[x];
                    rollcalc[x] = rollcalc[y];
                    rollcalc[y] = aux;
                }
            }
        }

        for (int i = 0; i < rollcalc.length; i++) {
            switch (rollcalc[i]) {
                case 0:
                    rollsDices += "R";
                    break;
                case 1:
                    rollsDices += "B";
                    break;
                case 2:
                    rollsDices += "Y";
                    break;
                case 3:
                    rollsDices += "S";
                    break;
            }
        }
    }

    private void abiliTimes() {
        for (int i = 0; i < 9; i++) {
            switch (skills[i][6]) {
                case "":
                case "0":
                    skills[i][6] = "1";
                    times[i] = Integer.parseInt(skills[i][6]);
                    break;
                case "R":
                case "B":
                case "Y":
                case "S":
                    times[i] = diceTimes(skills[i][6]);
                    break;
                default:
                    times[i] = Integer.parseInt(skills[i][6]);
                    break;
            }
            if (skills[i][7].equals("Deja Vu") || skills[i][7].equals("Physical Attack")) {
                if (!skills[i][12].equals("No Add Effect")) {
                    System.out.println("entrei");
                    increaseDejaPA(i);
                }
            }
        }
    }

    private int diceTimes(String dadao) {
        utils = new Roll();
        for (int i = 0; i < rollcalc.length; i++) {
            if (rollcalc[i] == 0) {
                utils.add(0);
            }
            if (rollcalc[i] == 1) {
                utils.add(1);
            }
            if (rollcalc[i] == 2) {
                utils.add(2);
            }
            if (rollcalc[i] == 3) {
                utils.add(3);
            }
        }

        int dadenho = 0;

        if ("R".equals(dadao)) {
            dadenho = 0;
        }
        if ("B".equals(dadao)) {
            dadenho = 1;
        }
        if ("Y".equals(dadao)) {
            dadenho = 2;
        }
        if ("S".equals(dadao)) {
            dadenho = 3;
        }

        Roll.Abidados d1 = utils.newAbidados(dadenho, 1);

        return utils.getTimes(new Roll.Abidados[]{d1});
    }

    private void increaseDejaPA(int x) {
        int value = times[x];
        int addvalue = Integer.parseInt(skills[x][13]);
        int addCounter = 0;
        String cbbOpera = skills[x][14];
        String cbbChangeable = skills[x][15];
        switch (skills[x][12]) {
            case "Dice":
                switch (skills[x][15]) {
                    case "Red":
                        for (int i = 0; i < rollcalc.length; i++) {
                            if (rollcalc[i] == 0) {
                                addCounter += 1;
                            }
                        }
                        break;
                    case "Blue":
                        for (int i = 0; i < rollcalc.length; i++) {
                            if (rollcalc[i] == 1) {
                                addCounter += 1;
                            }
                        }
                        break;
                    case "Yellow":
                        for (int i = 0; i < rollcalc.length; i++) {
                            if (rollcalc[i] == 2) {
                                addCounter += 1;
                            }
                        }
                        break;
                    case "Sword":
                        for (int i = 0; i < rollcalc.length; i++) {
                            if (rollcalc[i] == 3) {
                                addCounter += 1;
                            }
                        }
                        break;
                }
                switch (cbbOpera) {
                    case "[VAL] x":
                        if ("1".equals(skills[x][17]) || "2".equals(skills[x][17]) || "3".equals(skills[x][17])) {
                            value = againFullValue(x, skills[x][17], skills[x][18], value);
                        }
                        times[x] = value * addCounter;
                        break;
                    case "+[VAL] per":
                        times[x] = value + (addvalue * addCounter);
                        break;
                    case "-[VAL] per":
                        times[x] = value - (addvalue * addCounter);
                        break;
                }
                break;
            case "Opp Dice":
                addCounter = Integer.parseInt(skills[x][16]);
                switch (cbbOpera) {
                    case "[VAL] x":
                        if ("1".equals(skills[x][17]) || "2".equals(skills[x][17]) || "3".equals(skills[x][17])) {
                            value = againFullValue(x, skills[x][17], skills[x][18], value);
                        }
                        times[x] = value * addCounter;
                        break;
                    case "+[VAL] per":
                        times[x] = value + (addvalue * addCounter);
                        break;
                    case "-[VAL] per":
                        times[x] = value - (addvalue * addCounter);
                        break;
                }
                break;
            case "VS":
                switch (cbbOpera) {
                    case "[VAL] VS":
                        if (cbbChangeable.equals(opprace) || cbbChangeable.equals(oppguild) || cbbChangeable.equals(oppclas)) {
                            times[x] = Integer.parseInt(skills[x][13]);
                        } else {
                            times[x] = value;
                        }
                        break;
                    case "only VS":
                        times[x] = value;
                        break;
                }
                break;
            case "Ally":
                addCounter = Integer.parseInt(skills[x][16]);
                switch (cbbOpera) {
                    case "[VAL] x":
                        if ("1".equals(skills[x][17]) || "2".equals(skills[x][17]) || "3".equals(skills[x][17])) {
                            value = againFullValue(x, skills[x][17], skills[x][18], value);
                        }
                        times[x] = value * addCounter;
                        break;
                    case "+[VAL] per":
                        times[x] = value + (addvalue * addCounter);
                        break;
                    case "-[VAL] per":
                        times[x] = value - (addvalue * addCounter);
                        break;
                }
                break;
            case "Opp":
                addCounter = Integer.parseInt(skills[x][16]);
                switch (cbbOpera) {
                    case "[VAL] x":
                        if ("1".equals(skills[x][17]) || "2".equals(skills[x][17]) || "3".equals(skills[x][17])) {
                            value = againFullValue(x, skills[x][17], skills[x][18], value);
                        }
                        times[x] = value * addCounter;
                        break;
                    case "+[VAL] per":
                        times[x] = value + (addvalue * addCounter);
                        break;
                    case "-[VAL] per":
                        times[x] = value - (addvalue * addCounter);
                        break;
                }
                break;
            case "Attacker":
                switch (cbbOpera) {
                    case "[VAL] if atk":
                        if (rbtAtk.isSelected()) {
                            times[x] = Integer.parseInt(skills[x][13]);
                        } else {
                            times[x] = value;
                        }
                        break;
                    case "only if atk":
                        if (rbtAtk.isSelected()) {
                            times[x] = value;
                        }
                        break;
                }
                break;
            case "Defender":
                switch (cbbOpera) {
                    case "[VAL] if dfd":
                        if (rbtDfd.isSelected()) {
                            times[x] = Integer.parseInt(skills[x][13]);
                        } else {
                            times[x] = value;
                        }
                        break;
                    case "only if dfd":
                        if (rbtDfd.isSelected()) {
                            times[x] = value;
                        }
                        break;
                }
            case "Bonus/Malus":
                switch (cbbChangeable) {
                    case "Berserk":
                        addCounter = berserk;
                        break;
                    case "Blessing":
                        addCounter = blessing;
                        break;
                    case "Blizzard":
                        addCounter = blizzard;
                        break;
                    case "Bulwark":
                        addCounter = bulwark;
                        break;
                    case "Critical":
                        addCounter = critical;
                        break;
                    case "DMG+":
                        addCounter = moredamage;
                        break;
                    case "DMG -":
                        addCounter = malus;
                        break;
                    case "Dodge":
                        addCounter = dodge;
                        System.out.println("contei");
                        break;
                    case "Eclipse":
                        addCounter = eclipse;
                        break;
                    case "Frostbite":
                        addCounter = frostbite;
                        break;
                    case "Ice":
                        addCounter = ice;
                        break;
                    case "Powder":
                        addCounter = powder;
                        break;
                    case "Rage":
                        addCounter = rage;
                        break;
                    case "Resilience":
                        addCounter = resilience;
                        break;
                    case "Riposte":
                        addCounter = riposte;
                        break;
                    case "Rune":
                        addCounter = runes;
                        break;
                    case "Scarab":
                        addCounter = scarab;
                        break;
                    case "Shield":
                        addCounter = shield;
                        break;
                    case "Stench":
                        addCounter = stench;
                        break;
                    case "Storm":
                        addCounter = storm;
                        break;
                    case "Strength":
                        addCounter = str;
                        break;
                    case "Terror":
                        addCounter = terror;
                        break;
                    case "Thorn":
                        addCounter = thorn;
                        break;
                    case "Thunderstruck":
                        addCounter = thunderstruck;
                        break;
                }
                switch (cbbOpera) {
                    case "[VAL] x":
                        if ("1".equals(skills[x][17]) || "2".equals(skills[x][17]) || "3".equals(skills[x][17])) {
                            value = againFullValue(x, skills[x][17], skills[x][18], value);
                        }
                        times[x] = value * addCounter;
                        System.out.println("sai - " + times[x]);
                        break;
                    case "[VAL] +":
                        if ("1".equals(skills[x][17]) || "2".equals(skills[x][17]) || "3".equals(skills[x][17])) {
                            value = againFullValue(x, skills[x][17], skills[x][18], value);
                        }
                        times[x] = value + addCounter;
                        break;
                    case "[VAL] -":
                        if ("1".equals(skills[x][17]) || "2".equals(skills[x][17]) || "3".equals(skills[x][17])) {
                            value = againFullValue(x, skills[x][17], skills[x][18], value);
                        }
                        times[x] = value - addCounter;
                        break;
                    case "/ [VAL]":
                        if ("1".equals(skills[x][17]) || "2".equals(skills[x][17]) || "3".equals(skills[x][17])) {
                            value = againFullValue(x, skills[x][17], skills[x][18], value);
                        }
                        times[x] = addCounter / value;
                        break;
                    case "+[VAL] per":
                        times[x] = value + (addvalue * addCounter);
                        break;
                    case "-[VAL] per":
                        times[x] = value - (addvalue * addCounter);
                        break;
                }
                break;
            case "opp Bonus/Malus":
                switch (cbbChangeable) {
                    case "Berserk":
                        addCounter = oppberserk;
                        break;
                    case "Blessing":
                        addCounter = oppblessing;
                        break;
                    case "Blizzard":
                        addCounter = oppblizzard;
                        break;
                    case "Bulwark":
                        addCounter = oppbulwark;
                        break;
                    case "Critical":
                        addCounter = oppcritical;
                        break;
                    case "DMG+":
                        addCounter = oppmoredamage;
                        break;
                    case "DMG -":
                        addCounter = oppmalus;
                        break;
                    case "Dodge":
                        addCounter = oppdodge;
                        break;
                    case "Eclipse":
                        addCounter = oppeclipse;
                        break;
                    case "Frostbite":
                        addCounter = oppfrostbite;
                        break;
                    case "Ice":
                        addCounter = oppice;
                        break;
                    case "Powder":
                        addCounter = oppPowder;
                        break;
                    case "Rage":
                        addCounter = opprage;
                        break;
                    case "Resilience":
                        addCounter = oppresilience;
                        break;
                    case "Riposte":
                        addCounter = oppriposte;
                        break;
                    case "Rune":
                        addCounter = opprunes;
                        break;
                    case "Scarab":
                        addCounter = oppscarab;
                        break;
                    case "Shield":
                        addCounter = oppshield;
                        break;
                    case "Stench":
                        addCounter = oppstench;
                        break;
                    case "Storm":
                        addCounter = oppstorm;
                        break;
                    case "Strength":
                        addCounter = oppstr;
                        break;
                    case "Terror":
                        addCounter = oppterror;
                        break;
                    case "Thorn":
                        addCounter = oppthorn;
                        break;
                    case "Thunderstruck":
                        addCounter = oppthunderstruck;
                        break;
                }
                switch (cbbOpera) {
                    case "[VAL] x":
                        if ("1".equals(skills[x][17]) || "2".equals(skills[x][17]) || "3".equals(skills[x][17])) {
                            value = againFullValue(x, skills[x][17], skills[x][18], value);
                        }
                        times[x] = value * addCounter;
                        break;
                    case "[VAL] +":
                        if ("1".equals(skills[x][17]) || "2".equals(skills[x][17]) || "3".equals(skills[x][17])) {
                            value = againFullValue(x, skills[x][17], skills[x][18], value);
                        }
                        times[x] = value + addCounter;
                        break;
                    case "[VAL] -":
                        if ("1".equals(skills[x][17]) || "2".equals(skills[x][17]) || "3".equals(skills[x][17])) {
                            value = againFullValue(x, skills[x][17], skills[x][18], value);
                        }
                        times[x] = value - addCounter;
                        break;
                    case "/ [VAL]":
                        if ("1".equals(skills[x][17]) || "2".equals(skills[x][17]) || "3".equals(skills[x][17])) {
                            value = againFullValue(x, skills[x][17], skills[x][18], value);
                        }
                        times[x] = addCounter / value;
                        break;
                    case "+[VAL] per":
                        times[x] = value + (addvalue * addCounter);
                        break;
                    case "-[VAL] per":
                        times[x] = value - (addvalue * addCounter);
                        break;
                }
                break;
        }
        System.out.println(times[x]);
    }

    private void defender(int k) {
        switch (k) {
            case 0:
                for (int i = 0; i < (Integer) countHit1.getValue(); i++) {
                    if (scarab == 0) {
                        triggerThorn(thorn);
                        strGain(berserk);
                        if (blizcon != oldblizzard) {
                            if (checkIceAura.isSelected()) {
                                oppice += 2;
                            } else {
                                oppice += 1;
                            }
                            oldblizzard += 1;
                        }
                    } else {
                        scarab -= 1;
                    }
                }
                if (oppfrostbite > 0) {
                    triggerOppFrostbite((Integer) countActiv1.getValue());
                }
                if (oppterror > 0) {
                    triggerOppTerror((Integer) countActiv1.getValue());
                }
                break;
            case 1:
                for (int i = 0; i < (Integer) countHit2.getValue(); i++) {
                    if (scarab == 0) {
                        triggerThorn(thorn);
                        strGain(berserk);
                        if (blizcon != oldblizzard) {
                            if (checkIceAura.isSelected()) {
                                oppice += 2;
                            } else {
                                oppice += 1;
                            }
                            oldblizzard += 1;
                        }
                    } else {
                        scarab -= 1;
                    }
                }
                if (oppfrostbite > 0) {
                    triggerOppFrostbite((Integer) countActiv2.getValue());
                }
                if (oppterror > 0) {
                    triggerOppTerror((Integer) countActiv2.getValue());
                }
                break;
            default:
                for (int i = 0; i < (Integer) countHit3.getValue(); i++) {
                    if (scarab == 0) {
                        triggerThorn(thorn);
                        strGain(berserk);
                        if (blizcon != oldblizzard) {
                            if (checkIceAura.isSelected()) {
                                oppice += 2;
                            } else {
                                oppice += 1;
                            }
                            oldblizzard += 1;
                        }
                    } else {
                        scarab -= 1;
                    }
                }
                if (oppfrostbite > 0) {
                    triggerOppFrostbite((Integer) countActiv3.getValue());
                }
                if (oppterror > 0) {
                    triggerOppTerror((Integer) countActiv3.getValue());
                }
                for (int runecount = 0; runecount < runes; runecount++) {
                    Hit(200);
                }
                for (int shadowcount = 0; shadowcount < oppshadow; shadowcount++) {
                    Shock(300);
                }
                break;
        }
    }

    private void attacker(int k) {
        switch (k) {
            case 0:
                for (int i = 0; i < (Integer) countHit1.getValue(); i++) {
                    if (scarab == 0) {
                        triggerThorn(thorn);
                        strGain(berserk);
                        if (blizcon != oldblizzard) {
                            if (checkIceAura.isSelected()) {
                                oppice += 2;
                            } else {
                                oppice += 1;
                            }
                            oldblizzard += 1;
                        }
                    } else {
                        scarab -= 1;
                    }
                }
                if (oppfrostbite > 0) {
                    triggerOppFrostbite((Integer) countActiv1.getValue());
                }
                if (oppterror > 0) {
                    triggerOppTerror((Integer) countActiv1.getValue());
                }
                break;
            case 1:
                for (int i = 0; i < (Integer) countHit2.getValue(); i++) {
                    if (scarab == 0) {
                        triggerThorn(thorn);
                        strGain(berserk);
                        if (blizcon != oldblizzard) {
                            if (checkIceAura.isSelected()) {
                                oppice += 2;
                            } else {
                                oppice += 1;
                            }
                            oldblizzard += 1;
                        }
                    } else {
                        scarab -= 1;
                    }
                }
                if (oppfrostbite > 0) {
                    triggerOppFrostbite((Integer) countActiv2.getValue());
                }
                if (oppterror > 0) {
                    triggerOppTerror((Integer) countActiv2.getValue());
                }
                break;
            default:
                for (int i = 0; i < (Integer) countHit3.getValue(); i++) {
                    if (scarab == 0) {
                        triggerThorn(thorn);
                        strGain(berserk);
                        if (blizcon != oldblizzard) {
                            if (checkIceAura.isSelected()) {
                                oppice += 2;
                            } else {
                                oppice += 1;
                            }
                            oldblizzard += 1;
                        }
                    } else {
                        scarab -= 1;
                    }
                }
                if (oppfrostbite > 0) {
                    triggerOppFrostbite((Integer) countActiv3.getValue());
                }
                if (oppterror > 0) {
                    triggerOppTerror((Integer) countActiv3.getValue());
                }
                for (int runecount = 0; runecount < runes; runecount++) {
                    Hit(200);
                }
                for (int shadowcount = 0; shadowcount < oppshadow; shadowcount++) {
                    Shock(300);
                }
                swordphase();
                for (int i = 0; i < (Integer) countHitS.getValue(); i++) {
                    if (scarab == 0) {
                        triggerThorn(thorn);
                        strGain(berserk);
                        if (riposte > 0) {
                            triggerRiposte();
                        }
                    } else {
                        scarab -= 1;
                    }
                }
                break;
        }
    }

    private void buffei() {
        berserk += Integer.parseInt(txtYBerserk.getText());
        blessing += Integer.parseInt(txtYBlessing.getText());
        blizzard += Integer.parseInt(txtYBlizzard.getText());
        bulwark += Integer.parseInt(txtYBulwark.getText());
        critical += Integer.parseInt(txtYCritical.getText());
        moredamage += Integer.parseInt(txtYDMGBonus.getText());
        malus += Integer.parseInt(txtYDMGMalus.getText());
        dodge += Integer.parseInt(txtYDodge.getText());
        eclipse += Integer.parseInt(txtYEclipse.getText());
        frostbite += Integer.parseInt(txtYFrostbite.getText());
        ice += Integer.parseInt(txtYIce.getText());
        powder += Integer.parseInt(txtYPowder.getText());
        rage += Integer.parseInt(txtYRage.getText());
        resilience += Integer.parseInt(txtYResilience.getText());
        riposte += Integer.parseInt(txtYRiposte.getText());
        runes += Integer.parseInt(txtYRune.getText());
        scarab += Integer.parseInt(txtYScarab.getText());
        shield += Integer.parseInt(txtYShield.getText());
        stench += Integer.parseInt(txtYStench.getText());
        storm += Integer.parseInt(txtYStorm.getText());
        terror += Integer.parseInt(txtYTerror.getText());
        thorn += Integer.parseInt(txtYThorn.getText());
        thunderstruck += Integer.parseInt(txtYThunderstruck.getText());
        str += Integer.parseInt(txtYSTRBonus.getText());
        str -= Integer.parseInt(txtYSTRMalus.getText());
        if (str < 0) {
            str = 0;
        }
        oppberserk += Integer.parseInt(txtOBerserk.getText());
        oppblessing += Integer.parseInt(txtOBlessing.getText());
        oppblizzard += Integer.parseInt(txtOBlizzard.getText());
        oppbulwark += Integer.parseInt(txtOBulwark.getText());
        oppcritical += Integer.parseInt(txtOCritical.getText());
        oppmoredamage += Integer.parseInt(txtODMGBonus.getText());
        oppmalus += Integer.parseInt(txtODMGMalus.getText());
        oppdodge += Integer.parseInt(txtODodge.getText());
        oppeclipse += Integer.parseInt(txtOEclipse.getText());
        oppfrostbite += Integer.parseInt(txtOFrostbite.getText());
        oppice += Integer.parseInt(txtOIce.getText());
        oppPowder += Integer.parseInt(txtOPowder.getText());
        opprage += Integer.parseInt(txtORage.getText());
        oppresilience += Integer.parseInt(txtOResilience.getText());
        oppriposte += Integer.parseInt(txtORiposte.getText());
        opprunes += Integer.parseInt(txtORune.getText());
        oppscarab += Integer.parseInt(txtOScarab.getText());
        oppshield += Integer.parseInt(txtOShield.getText());
        oppstench += Integer.parseInt(txtOStench.getText());
        oppstorm += Integer.parseInt(txtOStorm.getText());
        oppterror += Integer.parseInt(txtOTerror.getText());
        oppthorn += Integer.parseInt(txtOThorn.getText());
        oppthunderstruck += Integer.parseInt(txtOThunderstruck.getText());
        oppstr += Integer.parseInt(txtOSTRBonus.getText());
        oppstr -= Integer.parseInt(txtOSTRMalus.getText());
        if (oppstr < 0) {
            oppstr = 0;
        }
    }

    private void sealing() {
        if (DMGSeal.isSelected()) {
            moredamage += 50;
        }
        if (DodgeSeal.isSelected()) {
            dodge += 1;
        }
        if (StrengthSeal.isSelected()) {
            str += 60;
        }
        if (ResilienceSeal.isSelected()) {
            moredamage += 80;
        }
        if (ShieldSeal.isSelected()) {
            shield += 400;
        }
        if (ThornSeal.isSelected()) {
            thorn += 70;
        }
        if (RageSeal.isSelected()) {
            rage += 20;
        }
        if (StenchSeal.isSelected()) {
            stench += 30;
        }
        if (BlessingSeal.isSelected()) {
            blessing += 20;
        }
    }

    private void triggerOppFrostbite(int n) {
        for (int i = 0; i < n; i++) {
            if (oppscarab == 0) {
                damage += oppfrostbite;
                fbdamage += oppfrostbite;
            } else {
                oppscarab -= 1;
            }
            freeze(1);
        }
    }

    private void triggerOppTerror(int n) {
        for (int i = 0; i < n; i++) {
            causeMalus(oppterror);
        }
    }

    private void triggerThorn(int n) {
        if (oppscarab == 0) {
            damage += thorn;
            thorndamage += thorn;
        } else {
            oppscarab -= 1;
        }
    }

    private void triggerRiposte() {
        Hit(str);
        riposte -= 1;
    }

    private void swordphase() {
        queroReusar();

        utils = new Roll();
        for (int i = 0; i < rollcalc.length; i++) {
            if (rollcalc[i] == 0) {
                utils.add(0);
            }
            if (rollcalc[i] == 1) {
                utils.add(1);
            }
            if (rollcalc[i] == 2) {
                utils.add(2);
            }
            if (rollcalc[i] == 3) {
                utils.add(3);
            }
        }
        Roll.Abidados dddd1 = utils.newAbidados(3, 1);

        for (int scount = 0; scount < utils.getTimes(new Roll.Abidados[]{dddd1}); scount++) {
            if (oppPowder == 0) {
                Hit(str);
            } else {
                Hit(str * 3);
                oppPowder -= 1;
            }
        }
    }

    private void useAbility() {
        oppstr -= stench;
        if (oppstr < 0) {
            oppstr = 0;
        }
        oppminusstr += stench;
        if (frostbite > 0) {
            if (scarab != 0) {
                Heal(frostbite);
                scarab -= 1;
            }
            ice += 1;
        }
        if (storm > 0) {
            if (oppscarab > 0) {
                Thunderstruck(storm / 10, true);
                oppscarab -= 1;
            } else {
                Lightning(storm);
            }
        }

        malus += terror;
    }

    private void causeMalus(int n) {
        if (checkMalusAura.isSelected()) {
            oppmalus += n + (n / 2);
        } else {
            oppmalus += n;
        }
    }

    private void freeze(int n) {
        if (checkIceAura.isSelected()) {
            oppice += n * 2;
        } else {
            oppice += n;
        }
    }

    private void strGain(int n) {
        if (checkStrAura.isSelected()) {
            str += n + (n * 3 / 10);
        } else {
            str += n;
        }
    }

    private void Avenge(int n) {
        if (!rbtWinning.isSelected()) {
            Hit(n + n / 2);
        } else {
            Hit(n);
        }
    }

    private void Backstab(int n) {
        Hit(n);
        oppminusstr += n / 2;
        oppstr -= n / 2;
        if (oppstr < 0) {
            oppstr = 0;
        }
    }

    private void Berserk(int n, boolean opp) {
        if (opp) {
            oppberserk += n;
        } else {
            if (checkBerserkAura.isSelected()) {
                berserk += n + (n * 5 / 10);
            } else {
                berserk += n;
            }
        }
    }

    private void Blessing(int n, boolean opp) {
        if (opp) {
            oppblessing += n;
        } else {
            blessing += n;
        }
    }

    private void Blizzard(int n, boolean opp) {
        if (opp) {
            oppblizzard += n;
        } else {
            blizzard += n;
        }
    }

    private void Bulwark(int n, boolean opp) {
        if (opp) {
            oppbulwark = n;
        } else {
            bulwark = n;
        }
    }

    private void Critical(int n, boolean opp) {
        if (opp) {
            oppcritical += n;
        } else {
            critical += n;
        }
    }

    private void Deathstare(int n) {
        int m = n - oppstr;
        if (m < 0) {
            m = 0;
        }
        Hit(m);
    }

    private void Dejavu() {
        djcounter += 1;
    }

    private void DMGBonus(int n, boolean opp) {
        if (opp) {
            oppmoredamage += n;
        } else {
            if (checkBonusDMGAura.isSelected()) {
                moredamage += n + (n * 4 / 10);
            } else {
                moredamage += n;
            }
        }
    }

    private void DMGMalus(int n, boolean opp) {
        if (opp) {
            causeMalus(n);
        } else {
            malus += n;
        }
    }

    private void DiceChange(int before, int after) {
        for (int j = 0; j < rollcalc.length; j++) {
            if (rollcalc[j] == before) {
                rollcalc[j] = after;
                used[j] = 1;
                break;
            }
        }
    }

    private void Dispel(int n) {
        dispelcounter += n;
    }

    private void Dodge(int n, boolean opp) {
        if (opp) {
            oppdodge += n;
        } else {
            if (checkDodgeAura.isSelected()) {
                dodge += n * 2;
            } else {
                dodge += n;
            }
        }
    }

    private void Eclipse(int n, boolean opp) {
        if (opp) {
            oppeclipse += n;
        } else {
            eclipse += n;
        }
    }

    private void Explode(int n) {
        if (oppPowder > 0) {
            Hit(n + (n * 2 / 5));
            oppPowder--;
        } else {
            Hit(n);
            oppPowder++;
        }
    }

    private void Fatigue(int n) {
        Hit(n);
        oppfatigue += n / 2;
    }

    private void Fireball(int n) {
        Hit(n);
        causeMalus(n / 10);
    }

    private void Frostbite(int n, boolean opp) {
        if (opp) {
            if (checkFrostbiteAura.isSelected()) {
                oppfrostbite += n + (n / 2);
            } else {
                oppfrostbite += n;
            }
        } else {
            frostbite += n;
        }
    }

    private void Hit(int n) {
        int stepdamage;
        if (critical > 0) {
            stepdamage = (n + moredamage - malus - fatigue) * 2 + oppPierce + oppthunderstruck;
            critical -= 1;
        } else {
            stepdamage = n + moredamage - malus - fatigue + oppPierce + oppthunderstruck;
        }
        fatigue = 0;
        oppPierce = 0;
        if (oppscarab > 0) {
            oppscarab -= 1;
            if (oppdodge > 0) {
                oppdodge -= 1;
            }
            if (oppeclipse > 0) {
                oppeclipse -= 1;
            }
        } else {

            if (oppdodge > 0) {
                stepdamage /= 2;
                oppdodge -= 1;
            }
            if (oppbulwark > 0) {
                stepdamage = oppbulwark;
            }
            int aux = stepdamage;
            stepdamage -= oppshield;
            oppshield -= aux;
            if (oppshield < 0) {
                oppshield = 0;
            }
            if (stepdamage < 0) {
                stepdamage = 0;
            }
            stepdamage -= oppresilience;
            damage += stepdamage;
            if (stepdamage > 0) {
                if (blessing > 0) {
                    Heal(blessing);
                }
                if (oppberserk > 0) {
                    oppstr += oppberserk;
                }
                if (rage > 0) {
                    strGain(rage);
                }
                if (oppthorn > 0) {
                    if (scarab > 0) {
                        Heal(oppthorn);
                        scarab -= 1;
                    } else {
                        if (shield > 0) {
                            shield -= oppthorn;
                            if (shield < 0) {
                                shield = 0;
                            }
                        }
                    }
                }
            }
        }

    }

    private void Heroism(int n) {
        if (rbtWinning.isSelected()) {
            Hit(n + n / 2);
        } else {
            Hit(n);
        }
    }

    private void Heal(int n) {
        if (eclipse == 0) {
            if (checkHealAura.isSelected()) {
                heal += n + (n * 5 / 10);
            } else {
                heal += n;
            }
        } else {
            eclipse -= 1;
        }
    }

    private void Ice(int n, boolean opp) {
        if (opp) {
            freeze(n);
        } else {
            ice += n;
        }
    }

    private void Iceball(int n) {
        Hit(n);
        freeze(1);
        DMGBonus(10 * oppice, false);
    }

    private void Inspire(int n) {
        haveinspire = 1;
        insp[n] = Integer.parseInt(skills[n][3]);
    }

    private void Kagejutsu(int n) {
        int kage = 0;
        if (oppberserk > 0) {
            kage++;
        }
        if (oppblessing > 0) {
            kage++;
        }
        if (oppblizzard > 0) {
            kage++;
        }
        if (oppbulwark > 0) {
            kage++;
        }
        if (oppcritical > 0) {
            kage++;
        }
        if (oppmoredamage > 0) {
            kage++;
        }
        if (oppdodge > 0) {
            kage++;
        }
        if (opprage > 0) {
            kage++;
        }
        if (oppresilience > 0) {
            kage++;
        }
        if (oppriposte > 0) {
            kage++;
        }
        if (opprunes > 0) {
            kage++;
        }
        if (oppscarab > 0) {
            kage++;
        }
        if (oppshield > 0) {
            kage++;
        }
        if (oppstench > 0) {
            kage++;
        }
        if (oppthorn > 0) {
            kage++;
        }
        Hit(n + (n / 5 * kage));
    }

    private void LifeDrain(int n) {
        Hit(n);
        Heal(n);
    }

    private void Lightning(int n) {
        Hit(n);
        Thunderstruck(n / 10, true);
    }

    private void Mimic(int n) {
        Hit(n);
        mmcounter += 1;
    }

    private void PhysicalAttack() {
        Hit(str);
    }

    private void Portal(int n) {
        Hit(n);
        int pttc = 0;
        if (eclipse > 0) {
            pttc++;
        }
        if (malus > 0) {
            pttc++;
        }
        if (ice > 0) {
            pttc++;
        }
        if (frostbite > 0) {
            pttc++;
        }
        if (thunderstruck > 0) {
            pttc++;
        }
        if (terror > 0) {
            pttc++;
        }
        if (powder > 0) {
            pttc++;
        }
        if (pttc == 1) {
            if (eclipse > 0) {
                oppeclipse+=eclipse;
            }
            if (malus > 0) {
                oppmalus+=malus;
            }
            if (ice > 0) {
                oppice+=ice;
            }
            if (frostbite > 0) {
                oppfrostbite+=frostbite;
            }
            if (thunderstruck > 0) {
                oppthunderstruck+=thunderstruck;
            }
            if (terror > 0) {
                oppterror+=terror;
            }
            if (powder > 0) {
                oppPowder+=powder;
            }
        } else {
            ptcounter += 1;
        }
    }

    private void Powder(int n, boolean opp) {
        if (opp) {
            oppPowder += n;
        } else {
            powder += n;
        }
    }

    private void Pierce(int n) {
        Hit(n);
        oppPierce += n / 2;
    }

    private void Purify(int n) {
        prcounter += n;
    }

    private void Rage(int n, boolean opp) {
        if (opp) {
            opprage += n;
        } else {
            if (checkRageAura.isSelected()) {
                rage += n + (n * 6 / 10);
            } else {
                rage += n;
            }
        }
    }

    private void Restore(int n) {
        int rest = 0;
        if (berserk > 0) {
            rest++;
        }
        if (blessing > 0) {
            rest++;
        }
        if (blizzard > 0) {
            rest++;
        }
        if (bulwark > 0) {
            rest++;
        }
        if (critical > 0) {
            rest++;
        }
        if (moredamage > 0) {
            rest++;
        }
        if (dodge > 0) {
            rest++;
        }
        if (rage > 0) {
            rest++;
        }
        if (resilience > 0) {
            rest++;
        }
        if (riposte > 0) {
            rest++;
        }
        if (runes > 0) {
            rest++;
        }
        if (scarab > 0) {
            rest++;
        }
        if (shield > 0) {
            rest++;
        }
        if (stench > 0) {
            rest++;
        }
        if (thorn > 0) {
            rest++;
        }
        Heal(n * rest);
    }

    private void Resilience(int n, boolean opp) {
        if (opp) {
            oppresilience += n;
        } else {
            if (checkResilienceAura.isSelected()) {
                resilience += n + (n / 2);
            } else {
                resilience += n;
            }
        }
    }

    private void Riposte(int n, boolean opp) {
        if (opp) {
            oppriposte += n;
        } else {
            riposte += n;
        }
    }

    private void Rune(int n, boolean opp) {
        if (opp) {
            opprunes += n;
        } else {
            runes += n;
        }
    }

    private void Scarab(int n, boolean opp) {
        if (opp) {
            oppscarab += n;
        } else {
            scarab += n;
            totalscarab += n;
        }
    }

    private void Shadow(int n, boolean opp) {
        if (opp) {
            oppshadow += n;
        } else {
            shadow += n;
        }
    }

    private void Shield(int n, boolean opp) {
        if (opp) {
            oppshield += n;
        } else {
            if (checkShieldAura.isSelected()) {
                shield += n + (n * 4 / 10);
            } else {
                shield += n;
            }
        }
    }

    private void Shieldbash(int x, int n) {
        if (alreadyConsumed[x] == 0) {
            switch (Integer.parseInt(skills[x][11])) {
                case 0:

                    break;
                case 1:
                    double sc1 = shield * 0.25;
                    shieldconsumed = (int) sc1;
                    shield -= shieldconsumed;
                    break;
                case 2:
                    double sc2 = shield * 0.5;
                    shieldconsumed = (int) sc2;
                    shield -= shieldconsumed;
                    break;
                case 3:
                    double sc3 = shield * 0.75;
                    shieldconsumed = (int) sc3;
                    shield -= shieldconsumed;
                    break;
                case 4:
                    shieldconsumed = shield;
                    shield = 0;
                    break;
            }
            if (x == 0 || x == 1 || x == 2) {
                alreadyConsumed[0] = 1;
                alreadyConsumed[1] = 1;
                alreadyConsumed[2] = 1;
            }
            if (x == 3 || x == 4 || x == 5) {
                alreadyConsumed[3] = 1;
                alreadyConsumed[4] = 1;
                alreadyConsumed[5] = 1;
            }
            if (x == 6 || x == 7 || x == 8) {
                alreadyConsumed[6] = 1;
                alreadyConsumed[7] = 1;
                alreadyConsumed[8] = 1;
            }
        }
        Hit(n + shield + (shield / 2));
        zeroshield = 1;
    }

    private void Shock(int n) {
        if (oppscarab > 0) {
            oppscarab -= 1;
            if (oppeclipse > 0) {
                oppeclipse -= 1;
            }
        } else {
            damage += n;
        }
        if (n > 0) {
            if (blessing > 0) {
                Heal(blessing);
            }
            if (oppberserk > 0) {
                oppstr += oppberserk;
            }
            if (rage > 0) {
                strGain(rage);
            }
            if (oppthorn > 0) {
                if (scarab > 0) {
                    Heal(oppthorn);
                    scarab -= 1;
                } else {
                    if (shield > 0) {
                        shield -= oppthorn;
                        if (shield < 0) {
                            shield = 0;
                        }
                    }
                }
            }
        }
    }

    private void Smite(int n) {
        Hit(n);
    }

    private void Spellbreaker(int n) {
        Hit(n);
        sbcounter += 1;
    }

    private void Stench(int n, boolean opp) {
        if (opp) {
            oppstench += n;
        } else {
            stench += n;
        }
    }

    private void Storm(int n, boolean opp) {
        if (opp) {
            oppstorm += n;
        } else {
            storm += n;
        }
    }

    private void StrPlus(int n, boolean opp) {
        if (opp) {
            oppstr += n;
        } else {
            strGain(n);
        }
    }

    private void StrMinus(int n, boolean opp) {
        if (opp) {
            oppstr -= n;
            oppminusstr += n;
        } else {
            str -= n;
        }
    }

    private void StrTimes(int n, boolean opp) {
        if (opp == true) {
            oppstr *= n;
        } else {
            strGain(str * (n - 1));
        }
    }

    private void StrEquals(int n, boolean opp) {
        if (opp) {
            oppstr = n;
            oppminusstr = 0;
            oppequalcounter = n;
        } else {
            str = n;
        }
    }

    private void StrDivide(int n, boolean opp) {
        if (n != 0) {
            if (opp) {
                oppstr /= n;
                oppdvdcounter *= n;
            } else {
                str /= n;
            }
        }
    }

    private void StrDrain(int n) {
        strGain(n);
        oppstr -= n;
        oppminusstr += n;
    }

    private void Terror(int n, boolean opp) {
        if (opp) {
            oppterror += n;
        } else {
            terror += n;
        }
    }

    private void Thorn(int n, boolean opp) {
        if (opp) {
            oppthorn += n;
        } else {
            if (checkThornAura.isSelected()) {
                thorn += n + (n * 3 / 10);
            } else {
                thorn += n;
            }
        }
    }

    private void Thunderstruck(int n, boolean opp) {
        if (opp) {
            if (checkThunderstruckAura.isSelected()) {
                oppthunderstruck += n + (n / 2);
            } else {
                oppthunderstruck += n;
            }
        } else {
            thunderstruck += n;
        }
    }

    private void discoverAbility(int x, int finalValue) {
        boolean opp = skills[x][2].equals("t");

        switch (skills[x][7]) {
            case "Avenge":
                Avenge(finalValue);
                break;
            case "Backstab":
                Backstab(finalValue);
                break;
            case "Berserk":
                Berserk(finalValue, opp);
                break;
            case "Blessing":
                Blessing(finalValue, opp);
                break;
            case "Blizzard":
                Blizzard(finalValue, opp);
                break;
            case "Bulwark":
                Bulwark(finalValue, opp);
                break;
            case "Critical":
                Critical(finalValue, opp);
                break;
            case "Death Stare":
                Deathstare(finalValue);
                break;
            case "Deja Vu":
                Dejavu();
                break;
            case "Dice Change":
                if (!opp) {
                    DiceChange(Integer.parseInt(skills[x][9]), Integer.parseInt(skills[x][10]));
                }
                break;
            case "Dispel":
                Dispel(finalValue);
                break;
            case "+DMG":
                DMGBonus(finalValue, opp);
                break;
            case "-DMG":
                DMGMalus(finalValue, opp);
                break;
            case "Dodge":
                Dodge(finalValue, opp);
                break;
            case "Eclipse":
                Eclipse(finalValue, opp);
                break;
            case "Explode":
                Explode(finalValue);
                break;
            case "Fatigue":
                Fatigue(finalValue);
                break;
            case "Fireball":
                Fireball(finalValue);
                break;
            case "Frostbite":
                Frostbite(finalValue, opp);
                break;
            case "Heal":
                Heal(finalValue);
                break;
            case "Heroism":
                Heroism(finalValue);
                break;
            case "Hit":
                Hit(finalValue);
                break;
            case "Ice":
                Ice(finalValue, opp);
                break;
            case "Iceball":
                Iceball(finalValue);
                break;
            case "Inspire":
                Inspire(x);
                break;
            case "Kagejutsu":
                Kagejutsu(finalValue);
                break;
            case "Life Drain":
                LifeDrain(finalValue);
                break;
            case "Lightning":
                Lightning(finalValue);
                break;
            case "Mimic":
                Mimic(finalValue);
                break;
            case "Physical Attack":
                PhysicalAttack();
                break;
            case "Pierce":
                Pierce(finalValue);
                break;
            case "Portal":
                Portal(finalValue);
                break;
            case "Powder":
                Powder(finalValue, opp);
                break;
            case "Purify":
                Purify(finalValue);
                break;
            case "Rage":
                Rage(finalValue, opp);
                break;
            case "Resilience":
                Resilience(finalValue, opp);
                break;
            case "Restore":
                Restore(finalValue);
                break;
            case "Riposte":
                Riposte(finalValue, opp);
                break;
            case "Rune":
                Rune(finalValue, opp);
                break;
            case "Scarab":
                Scarab(finalValue, opp);
                break;
            case "Shadow":
                Shadow(finalValue, opp);
                break;
            case "Shield":
                Shield(finalValue, opp);
                break;
            case "Shield Bash":
                Shieldbash(x, finalValue);
                break;
            case "Shock":
                Shock(finalValue);
                break;
            case "Smite":
                Smite(finalValue);
                break;
            case "Spellbreaker":
                Spellbreaker(finalValue);
                break;
            case "Stench":
                Stench(finalValue, opp);
                break;
            case "Storm":
                Storm(finalValue, opp);
                break;
            case "Strength +":
                StrPlus(finalValue, opp);
                break;
            case "Strength -":
                StrMinus(finalValue, opp);
                break;
            case "Strength x":
                StrTimes(finalValue, opp);
                break;
            case "Strength =":
                StrEquals(finalValue, opp);
                break;
            case "Strength /":
                StrDivide(finalValue, opp);
                break;
            case "Strength Drain":
                StrDrain(finalValue);
                break;
            case "Terror":
                Terror(finalValue, opp);
                break;
            case "Thorn":
                Thorn(finalValue, opp);
                break;
            case "Thunderstruck":
                Thunderstruck(finalValue, opp);
                break;
        }
    }

    private void rollsText() {
        total = damage + heal + shield + shieldconsumed + blizzard;
        details += (total);
        if (checkShield.isSelected() || checkHeal.isSelected()) {
            if (heal > 0 || ((shield + shieldconsumed) > 0 || blizzard > 0)) {
                details += "(";
                if (heal > 0) {
                    details += heal + " healed";
                }
                if ((shield + shieldconsumed) > 0) {
                    if (heal > 0) {
                        details += " &";
                    }
                    details += (shield + shieldconsumed) + " shield";
                }
                if (blizzard > 0) {
                    if (heal > 0 || (shield + shieldconsumed) > 0) {
                        details += " &";
                    }
                    details += blizzard + " blizzard";
                }
                details += ")";
            }
        }
        for (int j = 0; j < 9; j++) {
            if (all[j] > 0) {
                details += ", All ";
                if (skills[j][2].equals("t")) {
                    details += "opp ";
                }
                if (!skills[j][3].equals("0")) {
                    details += cbbAny.getItemAt(Integer.parseInt(skills[j][3])) + " ";
                }
                details += skills[j][7] + " " + all[j];
                if (skills[j][12].equals("if")) {
                    details += ", " + moreif[j] + " if " + skills[j][15];
                }
            }
        }
        if (oppterror > 0) {
            details += ", opp Terror " + oppterror;
        }
        if (oppmalus > 0) {
            details += ", opp DMG -" + oppmalus;
        }
        if (oppfatigue > 0) {
            details += ", opp Fatigue " + oppfatigue;
        }
        if (oppminusstr > 0) {
            details += ", opp STR -" + oppminusstr;
        }
        if (sbcounter > 0) {
            details += ", " + sbcounter + " spellbreaker(s)";
        }
        if (mmcounter > 0) {
            details += ", " + mmcounter + " mimic(s)";
        }
        if (ptcounter > 0) {
            details += ", " + ptcounter + " portal(s)";
        }
        if (dodge > 0) {
            details += ", " + dodge + " dodge(s)";
        }
        if (totalscarab > 0) {
            details += ", " + totalscarab + " scarab(s)";
        }
        if (resilience > 0) {
            details += ", " + resilience + " of resilience";
        }
        if (prcounter > 0) {
            details += ", " + prcounter + " Purify(ies)";
        }
        if (bulwark > 0) {
            details += ", Bulwark " + bulwark;
        }
        if (djcounter > 0) {
            details += ", " + djcounter + " Deja Vu(s)";
        }
        if (oppequalcounter > -1) {
            details += ", opp STR = " + oppequalcounter;
        }
        if (haveinspire != 0) {
            for (int i = 0; i < 9; i++) {
                if (insp[i] != 99) {
                    boolean foi = false;
                    if (i != 0) {
                        for (int k = i - 1; k != -1; k--) {
                            if (insp[i] == insp[k]) {
                                foi = true;
                            }
                        }
                    }
                    if (!foi) {
                        details += ", all ";
                        if (!skills[i][3].equals("0")) {
                            details += cbbAny.getItemAt(Integer.parseInt(skills[i][3])) + " ";
                        }
                        int howmanyaddrolls = 0;
                        for (int j = 0; j < 9; j++) {
                            if (insp[j] == insp[i]) {
                                howmanyaddrolls++;
                            }
                        }
                        details += "+" + howmanyaddrolls + " add. rolls";
                    }
                }
            }
        }

        if (oppdvdcounter > 1) {
            details += ", opp STR / " + oppdvdcounter;
        }
        /* if (!dispelList.isEmpty()) {
            details += ", Dispelled ";
            dispelList.forEach(dispelled -> {
                details += dispelled + " ";
            });
        }*/
        if (dispelcounter > 0) {
            details += ", " + dispelcounter + " dispel(s)";
        }

        java.awt.EventQueue.invokeLater(() -> {
            new ResultScreen(rollsDices + " = " + details, road).setVisible(true);
        });
    }

    public void allrollsText(int currentroll) {
        updateLabels();

        String first = labeling[0];
        if (skills[1][0].equals("yes")) {
            first += " & " + labeling[1];
        }
        if (skills[2][0].equals("yes")) {
            first += ", " + labeling[2];
        }
        String second = labeling[3];
        if (skills[4][0].equals("yes")) {
            second += " & " + labeling[4];
        }
        if (skills[5][0].equals("yes")) {
            second += ", " + labeling[5];
        }
        String third = labeling[6];
        if (skills[7][0].equals("yes")) {
            third += " & " + labeling[7];
        }
        if (skills[8][0].equals("yes")) {
            third += ", " + labeling[8];
        }

        String topao = texttop[0] + texttop[1] + texttop[2] + texttop[3] + texttop[4];
        String midao = textmid[0] + textmid[1] + textmid[2] + textmid[3] + textmid[4];
        String botao = textbot[0] + textbot[1] + textbot[2] + textbot[3] + textbot[4];

        int maiordetres = 0;

        if (topao.length() >= midao.length()) {
            if (topao.length() >= botao.length()) {
                maiordetres = topao.length();
            } else {
                maiordetres = botao.length();
            }
        } else {
            if (midao.length() >= botao.length()) {
                maiordetres = midao.length();
            } else {
                maiordetres = botao.length();
            }
        }

        String topaoT;
        String midaoT;
        String botaoT;

        if (maiordetres == 5) {
            if (topao.length() > 1) {
                if (topao.length() > 3) {
                    topaoT = "\t";
                } else {
                    topaoT = "\t\t";
                }
            } else {
                topaoT = "\t\t\t";
            }
            if (midao.length() > 1) {
                if (midao.length() > 3) {
                    midaoT = "\t";
                } else {
                    midaoT = "\t\t";
                }
            } else {
                midaoT = "\t\t\t";
            }
            if (botao.length() > 1) {
                if (botao.length() > 3) {
                    botaoT = "\t";
                } else {
                    botaoT = "\t\t";
                }
            } else {
                botaoT = "\t\t\t";
            }
        } else if (maiordetres == 4) {
            if (topao.length() > 1) {
                if (topao.length() > 3) {
                    topaoT = "\t";
                } else {
                    topaoT = "\t\t";
                }
            } else {
                topaoT = "\t\t\t";
            }
            if (midao.length() > 1) {
                if (midao.length() > 3) {
                    midaoT = "\t";
                } else {
                    midaoT = "\t\t";
                }
            } else {
                midaoT = "\t\t\t";
            }
            if (botao.length() > 1) {
                if (botao.length() > 3) {
                    botaoT = "\t";
                } else {
                    botaoT = "\t\t";
                }
            } else {
                botaoT = "\t\t\t";
            }
        } else if (maiordetres == 3) {
            if (topao.length() > 1) {
                topaoT = "\t";
            } else {
                topaoT = "\t\t";
            }
            if (midao.length() > 1) {
                midaoT = "\t";
            } else {
                midaoT = "\t\t";
            }
            if (botao.length() > 1) {
                botaoT = "\t";
            } else {
                botaoT = "\t\t";
            }
        } else if (maiordetres == 2) {
            if (topao.length() > 1) {
                topaoT = "\t";
            } else {
                topaoT = "\t\t";
            }
            if (midao.length() > 1) {
                midaoT = "\t";
            } else {
                midaoT = "\t\t";
            }
            if (botao.length() > 1) {
                botaoT = "\t";
            } else {
                botaoT = "\t\t";
            }
        } else if (maiordetres == 1) {
            topaoT = "\t";
            midaoT = "\t";
            botaoT = "\t";
        } else {
            topaoT = "\t\t";
            midaoT = "\t\t";
            botaoT = "\t\t";
        }
        setmade += "STR = " + txtStr.getText() + "\n";
        setmade += topao + topaoT + third + "\n";
        setmade += midao + midaoT + second + "\n";
        setmade += botao + botaoT + first + "\n\n";

        currentrow = "";
        currentrow += rollsDices + " = ";
        total = damage + heal + (shield + shieldconsumed) + blizzard;
        orderingDamage[currentroll][0] = total + "";
        currentrow += (total);
        if (checkShield.isSelected() || checkHeal.isSelected()) {
            if (heal > 0 || ((shield + shieldconsumed) > 0 || blizzard > 0)) {
                currentrow += "(";
                if (heal > 0) {
                    currentrow += heal + " healed";
                }
                if ((shield + shieldconsumed) > 0) {
                    if (heal > 0) {
                        currentrow += " &";
                    }
                    currentrow += (shield + shieldconsumed) + " shield";
                }
                if (blizzard > 0) {
                    if (heal > 0 || (shield + shieldconsumed) > 0) {
                        currentrow += " &";
                    }
                    currentrow += blizzard + " blizzard";
                }
                currentrow += ")";
            }
        }
        for (int j = 0; j < 9; j++) {
            if (all[j] > 0) {
                currentrow += ", All ";
                if (skills[j][2].equals("t")) {
                    currentrow += "opp ";
                }
                if (!skills[j][3].equals("0")) {
                    currentrow += cbbAny.getItemAt(Integer.parseInt(skills[j][3])) + " ";
                }
                currentrow += skills[j][7] + " " + all[j];
                if (skills[j][12].equals("if")) {
                    currentrow += ", " + moreif[j] + " if " + skills[j][15];
                }
            }
        }
        if (oppterror > 0) {
            currentrow += ", opp Terror " + oppterror;
        }
        if (oppmalus > 0) {
            currentrow += ", opp DMG -" + oppmalus;
        }
        if (oppfatigue > 0) {
            currentrow += ", opp Fatigue " + oppfatigue;
        }
        if (oppminusstr > 0) {
            currentrow += ", opp STR -" + oppminusstr;
        }
        if (sbcounter > 0) {
            currentrow += ", " + sbcounter + " spellbreaker(s)";
        }
        if (mmcounter > 0) {
            currentrow += ", " + mmcounter + " mimic(s)";
        }
        if (ptcounter > 0) {
            currentrow += ", " + ptcounter + " portal(s)";
        }
        if (dodge > 0) {
            currentrow += ", " + dodge + " dodge(s)";
        }
        if (totalscarab > 0) {
            currentrow += ", " + totalscarab + " scarab(s)";
        }
        if (resilience > 0) {
            currentrow += ", " + resilience + " of resilience";
        }
        if (prcounter > 0) {
            currentrow += ", " + prcounter + " Purify(ies)";
        }
        if (bulwark > 0) {
            currentrow += ", Bulwark " + bulwark;
        }
        if (djcounter > 0) {
            currentrow += ", " + djcounter + " Deja Vu(s)";
        }
        if (haveinspire != 0) {
            for (int i = 0; i < 9; i++) {
                if (insp[i] != 99) {
                    boolean foi = false;
                    if (i != 0) {
                        for (int k = i - 1; k != -1; k--) {
                            if (insp[i] == insp[k]) {
                                foi = true;
                            }
                        }
                    }
                    if (!foi) {
                        currentrow += ", all ";
                        if (!skills[i][3].equals("0")) {
                            currentrow += cbbAny.getItemAt(Integer.parseInt(skills[i][3])) + " ";
                        }
                        int howmanyaddrolls = 0;
                        for (int j = 0; j < 9; j++) {
                            if (insp[j] == insp[i]) {
                                howmanyaddrolls++;
                            }
                        }
                        currentrow += "+" + howmanyaddrolls + " add. rolls";
                    }
                }
            }
        }
        if (oppequalcounter > -1) {
            currentrow += ", opp STR = " + oppequalcounter;
        }
        if (oppdvdcounter > 1) {
            currentrow += ", opp STR / " + oppdvdcounter;
        }
        /*if (!dispelList.isEmpty()) {
            currentrow += ", Dispelled ";
            dispelList.forEach(dispelled -> {
                currentrow += dispelled + " ";
            });
        }*/
        if (dispelcounter > 0) {
            currentrow += ", " + dispelcounter + " dispel(s)";
        }
        alldetails += currentrow + "\n";
        orderingDamage[currentroll][1] = currentrow + "\n";
    }

    public void assumingText() {
        assuming = "";
        if (DMGSeal.isSelected()) {
            assuming += " +DMG Seal";
        }
        if (DodgeSeal.isSelected()) {
            assuming += " Dodge Seal";
        }
        if (RageSeal.isSelected()) {
            assuming += " Rage Seal";
        }
        if (ResilienceSeal.isSelected()) {
            assuming += " Resilience Seal";
        }
        if (ShieldSeal.isSelected()) {
            assuming += " Shield Seal";
        }
        if (StenchSeal.isSelected()) {
            assuming += " Stench Seal";
        }
        if (StrengthSeal.isSelected()) {
            assuming += " Strength Seal";
        }
        if (ThornSeal.isSelected()) {
            assuming += " Thorn Seal";
        }
        if (BlessingSeal.isSelected()) {
            assuming += " Blessing Seal";
        }

        if (!assuming.equals("")) {
            assuming = "Assuming: " + assuming + "\n\n";
        }
    }

    public void updateSupport() {
        if (checkSupport.isSelected()) {
            switch (guild) {
                case "Ava":
                    lblSupport.setText("+1000 Shield from Leon");
                    break;
                case "DN":
                    lblSupport.setText("+190 Resilience from Marek");
                    break;
                case "Kot":
                    lblSupport.setText("+80 Rage from Shozu");
                    break;
                case "Merc":
                    lblSupport.setText("+2 Criticals from Ugo");
                    break;
                case "Neh":
                    lblSupport.setText("+150 opp -DMG from Dead Izandra");
                    break;
                case "Noz":
                    lblSupport.setText("+150 +DMG from Zono");
                    break;
                case "Pir":
                    lblSupport.setText("+150 opp Tstruck from Volt");
                    break;
                case "RL":
                    lblSupport.setText("Not avaliable for the guild");
                    break;
                case "SH":
                    lblSupport.setText("+80 Berserk from Our");
                    break;
                case "SL":
                    lblSupport.setText("Not avaliable for the guild");
                    break;
                case "Temp":
                    lblSupport.setText("Not avaliable for the guild");
                    break;
                case "WT":
                    lblSupport.setText("Not avaliable for the guild");
                    break;
                case "Zil":
                    lblSupport.setText("+2 Dodge from Noz");
                    break;
            }
        }
    }

    public void giveSupport() {
        if (checkSupport.isSelected()) {
            switch (guild) {
                case "Ava":
                    shield += 1000;
                    break;
                case "DN":
                    resilience += 190;
                    break;
                case "Kot":
                    rage += 80;
                    break;
                case "Merc":
                    critical += 2;
                    break;
                case "Neh":
                    oppmalus += 150;
                    break;
                case "Noz":
                    moredamage += 150;
                    break;
                case "Pir":
                    oppthunderstruck += 150;
                    break;
                case "RL":
                    runes += 2;
                    break;
                case "SH":
                    berserk += 80;
                    break;
                case "SL":

                    break;
                case "Temp":
                    thorn += 180;
                    break;
                case "WT":
                    oppfrostbite += 200;
                    break;
                case "Zil":
                    dodge += 2;
                    break;

            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Seals = new javax.swing.ButtonGroup();
        AtkDef = new javax.swing.ButtonGroup();
        WinLoss = new javax.swing.ButtonGroup();
        pMain = new javax.swing.JPanel();
        pTitle = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblCM1 = new javax.swing.JLabel();
        pHeader = new javax.swing.JPanel();
        btnSave = new javax.swing.JButton();
        btnOpen = new javax.swing.JButton();
        btnLoad = new javax.swing.JButton();
        checkExperimental = new javax.swing.JCheckBox();
        btnReset = new javax.swing.JButton();
        btnHelp = new javax.swing.JButton();
        pBelow = new javax.swing.JPanel();
        pRoll = new javax.swing.JPanel();
        D1 = new JComboBox( diceImgs );
        D2 = new JComboBox( diceImgs );
        D3 = new JComboBox( diceImgs );
        D4 = new JComboBox( diceImgs );
        D5 = new JComboBox( diceImgs );
        D6 = new JComboBox( diceImgs );
        D7 = new JComboBox( diceImgs );
        checkExtra = new javax.swing.JCheckBox();
        btnCalc = new javax.swing.JButton();
        btnAll = new javax.swing.JButton();
        pBuffs = new javax.swing.JPanel();
        lblAura = new javax.swing.JLabel();
        lblTextSeal = new javax.swing.JLabel();
        lblSeal = new javax.swing.JLabel();
        checkSymb = new javax.swing.JCheckBox();
        lblBuff = new javax.swing.JLabel();
        btnBuffs = new javax.swing.JButton();
        lblCM12 = new javax.swing.JLabel();
        checkSupport = new javax.swing.JCheckBox();
        lblSupport = new javax.swing.JLabel();
        pShow = new javax.swing.JPanel();
        checkShield = new javax.swing.JCheckBox();
        checkHeal = new javax.swing.JCheckBox();
        checkBlizz = new javax.swing.JCheckBox();
        lblShow = new javax.swing.JLabel();
        checkTrans = new javax.swing.JCheckBox();
        pEmpty = new javax.swing.JPanel();
        pSkill = new javax.swing.JPanel();
        pWSkill = new javax.swing.JPanel();
        checkOpp = new javax.swing.JCheckBox();
        txtV = new javax.swing.JTextField();
        cbbDiceChanged = new JComboBox( diceImgs );
        cbbDiceChange = new JComboBox( diceImgs );
        lblTo = new javax.swing.JLabel();
        cbbAny = new javax.swing.JComboBox<>();
        lblWall = new javax.swing.JLabel();
        cbbAddDMG = new javax.swing.JComboBox<>();
        lblAdd = new javax.swing.JLabel();
        txtAddDMG = new javax.swing.JTextField();
        cbbChange = new javax.swing.JComboBox<>();
        lblShieldbash = new javax.swing.JLabel();
        cbbShieldbash = new javax.swing.JComboBox<>();
        txtN = new javax.swing.JTextField();
        cbbA = new javax.swing.JComboBox<>();
        cbbType = new javax.swing.JComboBox<>();
        checkType = new javax.swing.JCheckBox();
        checkAll = new javax.swing.JCheckBox();
        cbbOperation = new javax.swing.JComboBox<>();
        lblCounter = new javax.swing.JLabel();
        counter = new javax.swing.JSpinner();
        lblSpaceS = new javax.swing.JLabel();
        lblAnotherWall = new javax.swing.JLabel();
        lblAddAnother = new javax.swing.JLabel();
        txtAnotherAddDMG = new javax.swing.JTextField();
        cbbAnotherOperation = new javax.swing.JComboBox<>();
        cbbAnotherAddDMG = new javax.swing.JComboBox<>();
        txtShield = new javax.swing.JTextField();
        lblbuffdebuff = new javax.swing.JLabel();
        counterBuffDebuff = new javax.swing.JSpinner();
        pAuraSeal = new javax.swing.JPanel();
        pSeals = new javax.swing.JPanel();
        lblSealList = new javax.swing.JLabel();
        NoSeal = new javax.swing.JRadioButton();
        DMGSeal = new javax.swing.JRadioButton();
        DodgeSeal = new javax.swing.JRadioButton();
        RageSeal = new javax.swing.JRadioButton();
        ResilienceSeal = new javax.swing.JRadioButton();
        ShieldSeal = new javax.swing.JRadioButton();
        StenchSeal = new javax.swing.JRadioButton();
        StrengthSeal = new javax.swing.JRadioButton();
        ThornSeal = new javax.swing.JRadioButton();
        BlessingSeal = new javax.swing.JRadioButton();
        pAuras = new javax.swing.JPanel();
        lblAuraList = new javax.swing.JLabel();
        checkBerserkAura = new javax.swing.JCheckBox();
        checkBonusDMGAura = new javax.swing.JCheckBox();
        checkDodgeAura = new javax.swing.JCheckBox();
        checkFrostbiteAura = new javax.swing.JCheckBox();
        checkHealAura = new javax.swing.JCheckBox();
        checkIceAura = new javax.swing.JCheckBox();
        checkMalusAura = new javax.swing.JCheckBox();
        checkRageAura = new javax.swing.JCheckBox();
        checkResilienceAura = new javax.swing.JCheckBox();
        checkShieldAura = new javax.swing.JCheckBox();
        checkStrAura = new javax.swing.JCheckBox();
        checkThornAura = new javax.swing.JCheckBox();
        checkThunderstruckAura = new javax.swing.JCheckBox();
        pAssuming = new javax.swing.JPanel();
        pBlizz = new javax.swing.JPanel();
        lblBlizz = new javax.swing.JLabel();
        lblBlizz1 = new javax.swing.JLabel();
        countBlizz1 = new javax.swing.JSpinner();
        lblBlizz2 = new javax.swing.JLabel();
        countBlizz2 = new javax.swing.JSpinner();
        lblBlizz3 = new javax.swing.JLabel();
        countBlizz3 = new javax.swing.JSpinner();
        pHit = new javax.swing.JPanel();
        lblHit = new javax.swing.JLabel();
        lblHit1 = new javax.swing.JLabel();
        countHit1 = new javax.swing.JSpinner();
        lblHit2 = new javax.swing.JLabel();
        countHit2 = new javax.swing.JSpinner();
        lblHit3 = new javax.swing.JLabel();
        countHit3 = new javax.swing.JSpinner();
        lblHitS = new javax.swing.JLabel();
        countHitS = new javax.swing.JSpinner();
        pActiv = new javax.swing.JPanel();
        lblActiv = new javax.swing.JLabel();
        lblActiv1 = new javax.swing.JLabel();
        countActiv1 = new javax.swing.JSpinner();
        lblActiv2 = new javax.swing.JLabel();
        countActiv2 = new javax.swing.JSpinner();
        lblActiv3 = new javax.swing.JLabel();
        countActiv3 = new javax.swing.JSpinner();
        pBuffing = new javax.swing.JPanel();
        pTabs = new javax.swing.JTabbedPane();
        pYouTab = new javax.swing.JPanel();
        lblYBerserk = new javax.swing.JLabel();
        txtYBerserk = new javax.swing.JTextField();
        lblYBlessing = new javax.swing.JLabel();
        txtYBlessing = new javax.swing.JTextField();
        lblYBlizzard = new javax.swing.JLabel();
        txtYBlizzard = new javax.swing.JTextField();
        lblYBulwark = new javax.swing.JLabel();
        txtYBulwark = new javax.swing.JTextField();
        lblYCritical = new javax.swing.JLabel();
        txtYCritical = new javax.swing.JTextField();
        lblYDMGBonus = new javax.swing.JLabel();
        txtYDMGBonus = new javax.swing.JTextField();
        lblYDMGMalus = new javax.swing.JLabel();
        txtYDMGMalus = new javax.swing.JTextField();
        lblYDodge = new javax.swing.JLabel();
        txtYDodge = new javax.swing.JTextField();
        lblYEclipse = new javax.swing.JLabel();
        txtYEclipse = new javax.swing.JTextField();
        lblYFrostbite = new javax.swing.JLabel();
        txtYFrostbite = new javax.swing.JTextField();
        lblYIce = new javax.swing.JLabel();
        txtYIce = new javax.swing.JTextField();
        lblYPowder = new javax.swing.JLabel();
        txtYPowder = new javax.swing.JTextField();
        lblYRage = new javax.swing.JLabel();
        txtYRage = new javax.swing.JTextField();
        lblYResilience = new javax.swing.JLabel();
        txtYResilience = new javax.swing.JTextField();
        lblYRiposte = new javax.swing.JLabel();
        txtYRiposte = new javax.swing.JTextField();
        lblYRune = new javax.swing.JLabel();
        txtYRune = new javax.swing.JTextField();
        lblYScarab = new javax.swing.JLabel();
        txtYScarab = new javax.swing.JTextField();
        lblYShield = new javax.swing.JLabel();
        txtYShield = new javax.swing.JTextField();
        lblYStench = new javax.swing.JLabel();
        txtYStench = new javax.swing.JTextField();
        lblYSTRBonus = new javax.swing.JLabel();
        txtYSTRBonus = new javax.swing.JTextField();
        lblYSTRMalus = new javax.swing.JLabel();
        txtYSTRMalus = new javax.swing.JTextField();
        lblYTerror = new javax.swing.JLabel();
        txtYTerror = new javax.swing.JTextField();
        lblYThorn = new javax.swing.JLabel();
        txtYThorn = new javax.swing.JTextField();
        lblYThunderstruck = new javax.swing.JLabel();
        txtYThunderstruck = new javax.swing.JTextField();
        txtYStorm = new javax.swing.JTextField();
        lblYSTRMalus1 = new javax.swing.JLabel();
        pOppTab = new javax.swing.JPanel();
        lblOBerserk = new javax.swing.JLabel();
        txtOBerserk = new javax.swing.JTextField();
        lblOBlessing = new javax.swing.JLabel();
        txtOBlessing = new javax.swing.JTextField();
        lblOBlizzard = new javax.swing.JLabel();
        txtOBlizzard = new javax.swing.JTextField();
        lblOBulwark = new javax.swing.JLabel();
        txtOBulwark = new javax.swing.JTextField();
        lblOCritical = new javax.swing.JLabel();
        txtOCritical = new javax.swing.JTextField();
        lblODMGBonus = new javax.swing.JLabel();
        txtODMGBonus = new javax.swing.JTextField();
        txtODMGMalus = new javax.swing.JTextField();
        lblODMGMalus = new javax.swing.JLabel();
        lblODodge = new javax.swing.JLabel();
        txtODodge = new javax.swing.JTextField();
        lblOEclipse = new javax.swing.JLabel();
        txtOEclipse = new javax.swing.JTextField();
        lblOFrostbite = new javax.swing.JLabel();
        txtOFrostbite = new javax.swing.JTextField();
        lblOIce = new javax.swing.JLabel();
        txtOIce = new javax.swing.JTextField();
        lblOPowder = new javax.swing.JLabel();
        txtOPowder = new javax.swing.JTextField();
        lblORage = new javax.swing.JLabel();
        txtORage = new javax.swing.JTextField();
        lblOResilience = new javax.swing.JLabel();
        txtOResilience = new javax.swing.JTextField();
        lblORiposte = new javax.swing.JLabel();
        txtORiposte = new javax.swing.JTextField();
        lblORune = new javax.swing.JLabel();
        txtORune = new javax.swing.JTextField();
        lblOScarab = new javax.swing.JLabel();
        txtOScarab = new javax.swing.JTextField();
        lblOShield = new javax.swing.JLabel();
        txtOShield = new javax.swing.JTextField();
        lblOStench = new javax.swing.JLabel();
        txtOStench = new javax.swing.JTextField();
        lblOSTRBonus = new javax.swing.JLabel();
        txtOSTRBonus = new javax.swing.JTextField();
        lblOSTRMalus = new javax.swing.JLabel();
        txtOSTRMalus = new javax.swing.JTextField();
        lblOTerror = new javax.swing.JLabel();
        txtOTerror = new javax.swing.JTextField();
        lblOThorn = new javax.swing.JLabel();
        txtOThorn = new javax.swing.JTextField();
        lblOThunderstruck = new javax.swing.JLabel();
        txtOThunderstruck = new javax.swing.JTextField();
        txtOStorm = new javax.swing.JTextField();
        lblOSTRMalus1 = new javax.swing.JLabel();
        pChoose = new javax.swing.JPanel();
        pThird = new javax.swing.JPanel();
        pT1 = new javax.swing.JPanel();
        lblCM3 = new javax.swing.JLabel();
        lblT1 = new javax.swing.JLabel();
        pT2 = new javax.swing.JPanel();
        lblT2 = new javax.swing.JLabel();
        lblCM4 = new javax.swing.JLabel();
        pT3 = new javax.swing.JPanel();
        lblT3 = new javax.swing.JLabel();
        lblCM5 = new javax.swing.JLabel();
        btnTMinus = new javax.swing.JButton();
        btnTPlus = new javax.swing.JButton();
        TD1 = new JComboBox( diceNone );
        TD2 = new JComboBox( diceNone );
        TD3 = new JComboBox( diceNone );
        TD4 = new JComboBox( diceNone );
        lblTtoM = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        TD5 = new JComboBox( diceNone );
        pSecond = new javax.swing.JPanel();
        pM1 = new javax.swing.JPanel();
        lblM1 = new javax.swing.JLabel();
        lblCM6 = new javax.swing.JLabel();
        pM2 = new javax.swing.JPanel();
        lblM2 = new javax.swing.JLabel();
        lblCM7 = new javax.swing.JLabel();
        pM3 = new javax.swing.JPanel();
        lblM3 = new javax.swing.JLabel();
        lblCM8 = new javax.swing.JLabel();
        btnMMinus = new javax.swing.JButton();
        btnMPlus = new javax.swing.JButton();
        MD1 = new JComboBox( diceNone );
        MD2 = new JComboBox( diceNone );
        MD3 = new JComboBox( diceNone );
        MD4 = new JComboBox( diceNone );
        lblMtoT = new javax.swing.JLabel();
        lblMtoB = new javax.swing.JLabel();
        MD5 = new JComboBox( diceNone );
        pFirst = new javax.swing.JPanel();
        pB1 = new javax.swing.JPanel();
        lblB1 = new javax.swing.JLabel();
        lblCM9 = new javax.swing.JLabel();
        pB2 = new javax.swing.JPanel();
        lblB2 = new javax.swing.JLabel();
        lblCM10 = new javax.swing.JLabel();
        pB3 = new javax.swing.JPanel();
        lblB3 = new javax.swing.JLabel();
        lblCM11 = new javax.swing.JLabel();
        btnBMinus = new javax.swing.JButton();
        btnBPlus = new javax.swing.JButton();
        BD1 = new JComboBox( diceNone );
        BD2 = new JComboBox( diceNone );
        BD3 = new JComboBox( diceNone );
        BD4 = new JComboBox( diceNone );
        lblBtoM = new javax.swing.JLabel();
        BD5 = new JComboBox( diceNone );
        pYou = new javax.swing.JPanel();
        lblYou = new javax.swing.JLabel();
        lblSTR = new javax.swing.JLabel();
        txtStr = new javax.swing.JTextField();
        cbbGuild = new JComboBox( guilds );
        cbbRace = new JComboBox( races );
        cbbClass = new JComboBox( classes );
        pOpp = new javax.swing.JPanel();
        lblOpp = new javax.swing.JLabel();
        lblOppSTR = new javax.swing.JLabel();
        txtOppStr = new javax.swing.JTextField();
        cbbOppGuild = new JComboBox( guilds );
        cbbOppRace = new JComboBox( races );
        cbbOppClass = new JComboBox( classes );
        pAssume = new javax.swing.JPanel();
        lblAssuming = new javax.swing.JLabel();
        lblYouAre = new javax.swing.JLabel();
        rbtAtk = new javax.swing.JRadioButton();
        rbtDfd = new javax.swing.JRadioButton();
        lblCM2 = new javax.swing.JLabel();
        pSecond1 = new javax.swing.JPanel();
        lblDBOT4 = new javax.swing.JLabel();
        lblDBOT3 = new javax.swing.JLabel();
        lblDBOT2 = new javax.swing.JLabel();
        lblDBOT1 = new javax.swing.JLabel();
        lblAbyBOT = new javax.swing.JLabel();
        lblB = new javax.swing.JLabel();
        lblDBOT5 = new javax.swing.JLabel();
        pSecond2 = new javax.swing.JPanel();
        lblDMID1 = new javax.swing.JLabel();
        lblDMID2 = new javax.swing.JLabel();
        lblDMID3 = new javax.swing.JLabel();
        lblDMID4 = new javax.swing.JLabel();
        lblAbyMID = new javax.swing.JLabel();
        lblM = new javax.swing.JLabel();
        lblDMID5 = new javax.swing.JLabel();
        pSecond3 = new javax.swing.JPanel();
        lblDTOP4 = new javax.swing.JLabel();
        lblDTOP3 = new javax.swing.JLabel();
        lblDTOP2 = new javax.swing.JLabel();
        lblDTOP1 = new javax.swing.JLabel();
        lblAbyTOP = new javax.swing.JLabel();
        lblT = new javax.swing.JLabel();
        lblDTOP5 = new javax.swing.JLabel();
        pSecond4 = new javax.swing.JPanel();
        counter1 = new javax.swing.JSpinner();
        lblCounter1 = new javax.swing.JLabel();
        lblWL = new javax.swing.JLabel();
        rbtWinning = new javax.swing.JRadioButton();
        rbtLosing = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ciramolator - Eredan Arena's Set Simulator");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/IMG/logicon.png")).getImage());
        setPreferredSize(new java.awt.Dimension(1330, 760));
        setResizable(false);
        setSize(new java.awt.Dimension(1330, 760));

        pMain.setBackground(new java.awt.Color(119, 157, 202));
        pMain.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pTitle.setBackground(new java.awt.Color(40, 80, 126));
        pTitle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(57, 98, 145)));
        pTitle.setPreferredSize(new java.awt.Dimension(350, 100));
        pTitle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pTitleMouseClicked(evt);
            }
        });

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/FinalLogo.png"))); // NOI18N

        lblCM1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCM1.setForeground(new java.awt.Color(54, 97, 146));
        lblCM1.setText("CLICK ME");

        javax.swing.GroupLayout pTitleLayout = new javax.swing.GroupLayout(pTitle);
        pTitle.setLayout(pTitleLayout);
        pTitleLayout.setHorizontalGroup(
            pTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTitleLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
            .addGroup(pTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pTitleLayout.createSequentialGroup()
                    .addComponent(lblCM1)
                    .addGap(0, 289, Short.MAX_VALUE)))
        );
        pTitleLayout.setVerticalGroup(
            pTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
            .addGroup(pTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pTitleLayout.createSequentialGroup()
                    .addComponent(lblCM1)
                    .addGap(0, 31, Short.MAX_VALUE)))
        );

        pMain.add(pTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 360, 50));

        pHeader.setBackground(new java.awt.Color(92, 129, 172));
        pHeader.setPreferredSize(new java.awt.Dimension(1030, 100));

        btnSave.setBackground(new java.awt.Color(21, 58, 100));
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("Save this set to PC");
        btnSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnOpen.setBackground(new java.awt.Color(21, 58, 100));
        btnOpen.setForeground(new java.awt.Color(255, 255, 255));
        btnOpen.setText("Open a set from PC");
        btnOpen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenActionPerformed(evt);
            }
        });

        btnLoad.setBackground(new java.awt.Color(21, 58, 100));
        btnLoad.setForeground(new java.awt.Color(255, 255, 255));
        btnLoad.setText("Load a set from game");
        btnLoad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadActionPerformed(evt);
            }
        });

        checkExperimental.setBackground(new java.awt.Color(92, 129, 172));
        checkExperimental.setForeground(new java.awt.Color(255, 255, 255));
        checkExperimental.setText("Experimental Content");
        checkExperimental.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkExperimental.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkExperimentalItemStateChanged(evt);
            }
        });

        btnReset.setBackground(new java.awt.Color(21, 58, 100));
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setText("Reset");
        btnReset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnHelp.setBackground(new java.awt.Color(21, 58, 100));
        btnHelp.setForeground(new java.awt.Color(255, 255, 255));
        btnHelp.setText("?");
        btnHelp.setMargin(new java.awt.Insets(2, 5, 2, 5));
        btnHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHelpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pHeaderLayout = new javax.swing.GroupLayout(pHeader);
        pHeader.setLayout(pHeaderLayout);
        pHeaderLayout.setHorizontalGroup(
            pHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(checkExperimental)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHelp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 310, Short.MAX_VALUE)
                .addComponent(btnReset)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLoad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnOpen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSave)
                .addGap(72, 72, 72))
        );
        pHeaderLayout.setVerticalGroup(
            pHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSave)
                        .addComponent(btnOpen)
                        .addComponent(btnLoad)
                        .addComponent(btnReset))
                    .addGroup(pHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(checkExperimental)
                        .addComponent(btnHelp)))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pMain.add(pHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 0, 1020, 50));

        pBelow.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pRoll.setBackground(new java.awt.Color(119, 157, 202));
        pRoll.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(95, 133, 179)));
        pRoll.setPreferredSize(new java.awt.Dimension(350, 100));

        D1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        D1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                D1KeyTyped(evt);
            }
        });

        D2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        D2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                D2KeyTyped(evt);
            }
        });

        D3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        D3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                D3KeyTyped(evt);
            }
        });

        D4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        D4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                D4KeyTyped(evt);
            }
        });

        D5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        D5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                D5KeyTyped(evt);
            }
        });

        D6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        D6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                D6KeyTyped(evt);
            }
        });

        D7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        D7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                D7KeyTyped(evt);
            }
        });

        checkExtra.setBackground(new java.awt.Color(119, 157, 202));
        checkExtra.setText("Extra Dice");
        checkExtra.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkExtraItemStateChanged(evt);
            }
        });

        btnCalc.setBackground(new java.awt.Color(21, 58, 100));
        btnCalc.setForeground(new java.awt.Color(255, 255, 255));
        btnCalc.setText("Calculate");
        btnCalc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCalc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcActionPerformed(evt);
            }
        });

        btnAll.setBackground(new java.awt.Color(21, 58, 100));
        btnAll.setForeground(new java.awt.Color(255, 255, 255));
        btnAll.setText("Calculate All Rolls");
        btnAll.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAllActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pRollLayout = new javax.swing.GroupLayout(pRoll);
        pRoll.setLayout(pRollLayout);
        pRollLayout.setHorizontalGroup(
            pRollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pRollLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pRollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pRollLayout.createSequentialGroup()
                        .addComponent(D1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(D2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(D3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(D7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pRollLayout.createSequentialGroup()
                        .addGap(0, 110, Short.MAX_VALUE)
                        .addComponent(btnCalc)
                        .addGap(18, 18, 18)
                        .addComponent(btnAll))
                    .addGroup(pRollLayout.createSequentialGroup()
                        .addComponent(D4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(D5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(D6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(checkExtra)))
                .addContainerGap())
        );
        pRollLayout.setVerticalGroup(
            pRollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pRollLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pRollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(D1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pRollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(checkExtra)
                    .addComponent(D6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(D4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pRollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAll)
                    .addComponent(btnCalc))
                .addContainerGap(78, Short.MAX_VALUE))
        );

        pBelow.add(pRoll, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 170));

        pBuffs.setBackground(new java.awt.Color(92, 129, 172));
        pBuffs.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(48, 83, 124)));
        pBuffs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pBuffsMouseClicked(evt);
            }
        });

        lblAura.setForeground(new java.awt.Color(255, 255, 255));
        lblAura.setText(" Aura(s):");
        lblAura.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(68, 108, 154)));
        lblAura.setPreferredSize(new java.awt.Dimension(42, 20));

        lblTextSeal.setForeground(new java.awt.Color(255, 255, 255));
        lblTextSeal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(68, 108, 154)));

        lblSeal.setForeground(new java.awt.Color(255, 255, 255));
        lblSeal.setText("Seal:");
        lblSeal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(68, 108, 154)));

        checkSymb.setBackground(new java.awt.Color(92, 129, 172));
        checkSymb.setForeground(new java.awt.Color(255, 255, 255));
        checkSymb.setText("Symbiosis Active");
        checkSymb.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 255)));

        lblBuff.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblBuff.setForeground(new java.awt.Color(255, 255, 255));
        lblBuff.setText("Buffs");

        btnBuffs.setBackground(new java.awt.Color(21, 58, 100));
        btnBuffs.setForeground(new java.awt.Color(255, 255, 255));
        btnBuffs.setText("Buffs & Debuffs");
        btnBuffs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuffsActionPerformed(evt);
            }
        });

        lblCM12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCM12.setForeground(new java.awt.Color(119, 155, 197));
        lblCM12.setText("CLICK ME");

        checkSupport.setBackground(new java.awt.Color(92, 129, 172));
        checkSupport.setForeground(new java.awt.Color(255, 255, 255));
        checkSupport.setText("Guild Support active");
        checkSupport.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkSupportItemStateChanged(evt);
            }
        });

        lblSupport.setForeground(new java.awt.Color(255, 255, 255));
        lblSupport.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(68, 108, 154)));

        javax.swing.GroupLayout pBuffsLayout = new javax.swing.GroupLayout(pBuffs);
        pBuffs.setLayout(pBuffsLayout);
        pBuffsLayout.setHorizontalGroup(
            pBuffsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pBuffsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pBuffsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pBuffsLayout.createSequentialGroup()
                        .addGroup(pBuffsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pBuffsLayout.createSequentialGroup()
                                .addComponent(lblSeal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblTextSeal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblSupport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pBuffsLayout.createSequentialGroup()
                                .addGroup(pBuffsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblAura, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pBuffsLayout.createSequentialGroup()
                                        .addComponent(btnBuffs)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(checkSupport)))
                                .addGap(0, 130, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(pBuffsLayout.createSequentialGroup()
                        .addComponent(lblBuff)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(checkSymb)
                        .addGap(18, 18, 18)
                        .addComponent(lblCM12, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        pBuffsLayout.setVerticalGroup(
            pBuffsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pBuffsLayout.createSequentialGroup()
                .addGroup(pBuffsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pBuffsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pBuffsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblBuff)
                            .addComponent(checkSymb)))
                    .addComponent(lblCM12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pBuffsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblSeal)
                    .addComponent(lblTextSeal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSupport, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(lblAura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pBuffsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuffs)
                    .addComponent(checkSupport))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pBelow.add(pBuffs, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 0, 390, 170));

        pShow.setBackground(new java.awt.Color(119, 157, 202));

        checkShield.setBackground(new java.awt.Color(119, 157, 202));
        checkShield.setText("Shield");

        checkHeal.setBackground(new java.awt.Color(119, 157, 202));
        checkHeal.setText("Heal");

        checkBlizz.setBackground(new java.awt.Color(119, 157, 202));
        checkBlizz.setText("Blizzard");

        lblShow.setBackground(new java.awt.Color(119, 157, 202));
        lblShow.setText("Show:");

        checkTrans.setBackground(new java.awt.Color(119, 157, 202));
        checkTrans.setText("Transcedence");
        checkTrans.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkTransItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout pShowLayout = new javax.swing.GroupLayout(pShow);
        pShow.setLayout(pShowLayout);
        pShowLayout.setHorizontalGroup(
            pShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pShowLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pShowLayout.createSequentialGroup()
                        .addComponent(lblShow)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkBlizz)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(checkHeal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(checkShield))
                    .addComponent(checkTrans))
                .addContainerGap(131, Short.MAX_VALUE))
        );
        pShowLayout.setVerticalGroup(
            pShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pShowLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkBlizz)
                    .addComponent(checkHeal)
                    .addComponent(checkShield)
                    .addComponent(lblShow))
                .addGap(18, 18, 18)
                .addComponent(checkTrans)
                .addContainerGap(99, Short.MAX_VALUE))
        );

        pBelow.add(pShow, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 340, 170));

        pEmpty.setBackground(new java.awt.Color(119, 157, 202));

        javax.swing.GroupLayout pEmptyLayout = new javax.swing.GroupLayout(pEmpty);
        pEmpty.setLayout(pEmptyLayout);
        pEmptyLayout.setHorizontalGroup(
            pEmptyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 410, Short.MAX_VALUE)
        );
        pEmptyLayout.setVerticalGroup(
            pEmptyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );

        pBelow.add(pEmpty, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 0, 410, 170));

        pMain.add(pBelow, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 580, 1380, 180));

        pSkill.setBackground(new java.awt.Color(68, 108, 154));

        pWSkill.setBackground(new java.awt.Color(119, 157, 202));
        pWSkill.setBorder(        javax.swing.BorderFactory.createLineBorder(new java.awt.Color(40, 80, 126), 2));
        pWSkill.setPreferredSize(new java.awt.Dimension(910, 154));

        checkOpp.setBackground(new java.awt.Color(119, 157, 202));
        checkOpp.setText("Opp");
        checkOpp.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbAnotherOperationItemStateChanged(evt);
            }
        });

        txtV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAnotherAddDMGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        cbbDiceChanged.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbbDiceChanged.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbAnotherOperationItemStateChanged(evt);
            }
        });
        cbbDiceChanged.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cbbDiceChangedKeyTyped(evt);
            }
        });

        cbbDiceChange.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cbbDiceChange.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbAnotherOperationItemStateChanged(evt);
            }
        });
        cbbDiceChange.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cbbDiceChangeKeyTyped(evt);
            }
        });

        lblTo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/pArrowD2D.png"))); // NOI18N

        cbbAny.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Any", "Ava", "DN", "Kot", "Merc", "Neh", "Noz", "Pir", "RL", "SH", "SL", "Temp", "WT", "Zil", "Bard", "Berserker", "Colossus", "Craftsman", "Mage", "Marauder", "Priest", "UnkClass", "Warrior", "Beast", "Dais", "Demon", "Dragon", "Elfine", "Eltarite", "Golem", "Guem", "Homchai", "Human", "Ice Elf", "Solarian", "Undead", "UnkRace" }));
        cbbAny.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbAnotherOperationItemStateChanged(evt);
            }
        });

        lblWall.setBackground(new java.awt.Color(106, 144, 189));
        lblWall.setOpaque(true);

        cbbAddDMG.setMaximumRowCount(12);
        cbbAddDMG.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No Add Effect", "Dice", "Opp Dice", "VS", "Ally", "Opp", "Attacker", "Defender", "Bonus/Malus", "opp Bonus/Malus" }));
        cbbAddDMG.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbAddDMGItemStateChanged(evt);
            }
        });

        lblAdd.setText("Additional Effect");

        txtAddDMG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAnotherAddDMGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        cbbChange.setMaximumRowCount(12);
        cbbChange.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Changeable" }));
        cbbChange.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbChangeItemStateChanged(evt);
            }
        });
        cbbChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbChangeActionPerformed(evt);
            }
        });

        lblShieldbash.setText("Shield Pre-Consumed:");

        cbbShieldbash.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0%", "25%", "50%", "75%", "100%" }));
        cbbShieldbash.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbAnotherOperationItemStateChanged(evt);
            }
        });

        txtN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAnotherAddDMGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNKeyTyped(evt);
            }
        });

        cbbA.setMaximumRowCount(15);
        cbbA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Backstab", "Berserk", "Blessing", "Blizzard", "Bulwark", "Critical", "Death Stare", "Deja Vu", "Dice Change", "Dispel", "+DMG", "-DMG", "Dodge", "Eclipse", "Fireball", "Frostbite", "Heal", "Hit", "Ice", "Inspire", "Life Drain", "Lightning", "Mimic", "Physical Attack", "Portal", "Powder", "Purify", "Rage", "Resilience", "Riposte", "Rune", "Scarab", "Shield", "Shield Bash", "Shock", "Smite", "Spellbreaker", "Stench", "Storm", "Strength +", "Strength -", "Strength /", "Strength =", "Strength Drain", "Strength x", "Symbiosis", "Terror", "Thorn", "Thunderstruck" }));
        cbbA.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbAItemStateChanged(evt);
            }
        });

        cbbType.setMaximumRowCount(12);
        cbbType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "Brave", "Corrupt", "Icy", "Necrotic", "Noble", "Runic" }));
        cbbType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbTypeItemStateChanged(evt);
            }
        });

        checkType.setBackground(new java.awt.Color(119, 157, 202));
        checkType.setText("Changeable");
        checkType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbAnotherOperationItemStateChanged(evt);
            }
        });

        checkAll.setBackground(new java.awt.Color(119, 157, 202));
        checkAll.setText("All");
        checkAll.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkAllItemStateChanged(evt);
            }
        });

        cbbOperation.setMaximumRowCount(12);
        cbbOperation.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Changeable" }));
        cbbOperation.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbOperationItemStateChanged(evt);
            }
        });

        lblCounter.setText("Changeable");

        counter.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                counterStateChanged(evt);
            }
        });

        lblAnotherWall.setBackground(new java.awt.Color(106, 144, 189));
        lblAnotherWall.setOpaque(true);

        lblAddAnother.setText("Additional Effect");

        txtAnotherAddDMG.setText("0");
        txtAnotherAddDMG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAnotherAddDMGKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        cbbAnotherOperation.setMaximumRowCount(12);
        cbbAnotherOperation.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ava", "DN", "Kot", "Merc", "Neh", "Noz", "Pir", "RL", "SH", "SL", "Temp", "WT", "Zil", "Bard", "Berserker", "Colossus", "Craftsman", "Mage", "Marauder", "Priest", "UnkClass", "Warrior", "Beast", "Dais", "Demon", "Dragon", "Elfine", "Eltarite", "Golem", "Guem", "Homchai", "Human", "Ice Elf", "Solarian", "Undead", "UnkRace" }));
        cbbAnotherOperation.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbAnotherOperationItemStateChanged(evt);
            }
        });

        cbbAnotherAddDMG.setMaximumRowCount(12);
        cbbAnotherAddDMG.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No Add Effect", "[VAL] VS", "[VAL] if atk", "[VAL] if dfd" }));
        cbbAnotherAddDMG.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbAnotherAddDMGItemStateChanged(evt);
            }
        });

        txtShield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtShieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtShieldKeyTyped(evt);
            }
        });

        lblbuffdebuff.setText("nmb of");

        counterBuffDebuff.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                counterBuffDebuffStateChanged(evt);
            }
        });

        javax.swing.GroupLayout pWSkillLayout = new javax.swing.GroupLayout(pWSkill);
        pWSkill.setLayout(pWSkillLayout);
        pWSkillLayout.setHorizontalGroup(
            pWSkillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pWSkillLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pWSkillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pWSkillLayout.createSequentialGroup()
                        .addComponent(txtN, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pWSkillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pWSkillLayout.createSequentialGroup()
                                .addComponent(checkAll)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(checkOpp)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbbAny, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pWSkillLayout.createSequentialGroup()
                                .addComponent(cbbA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtV, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pWSkillLayout.createSequentialGroup()
                                .addComponent(cbbType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(checkType))
                            .addGroup(pWSkillLayout.createSequentialGroup()
                                .addComponent(cbbDiceChange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTo, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbbDiceChanged, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblbuffdebuff)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(counterBuffDebuff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pWSkillLayout.createSequentialGroup()
                        .addComponent(lblShieldbash)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbShieldbash, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(56, 56, 56)
                .addComponent(lblWall, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pWSkillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAdd)
                    .addGroup(pWSkillLayout.createSequentialGroup()
                        .addComponent(txtAddDMG, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pWSkillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbAddDMG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pWSkillLayout.createSequentialGroup()
                                .addComponent(cbbOperation, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(pWSkillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtShield)
                                    .addComponent(cbbChange, 0, 109, Short.MAX_VALUE)))))
                    .addGroup(pWSkillLayout.createSequentialGroup()
                        .addComponent(lblCounter)
                        .addGap(18, 18, 18)
                        .addComponent(counter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(lblAnotherWall, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pWSkillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pWSkillLayout.createSequentialGroup()
                        .addComponent(lblAddAnother)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addComponent(lblSpaceS, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pWSkillLayout.createSequentialGroup()
                        .addComponent(txtAnotherAddDMG, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pWSkillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbAnotherOperation, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbAnotherAddDMG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(335, 335, 335))
        );
        pWSkillLayout.setVerticalGroup(
            pWSkillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblWall, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblAnotherWall, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pWSkillLayout.createSequentialGroup()
                .addGroup(pWSkillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pWSkillLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pWSkillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(checkAll)
                            .addComponent(checkOpp)
                            .addComponent(cbbAny, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pWSkillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkType))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pWSkillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(pWSkillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(cbbDiceChange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbDiceChanged, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTo, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblbuffdebuff)
                            .addComponent(counterBuffDebuff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pWSkillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(cbbShieldbash, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblShieldbash)))
                    .addGroup(pWSkillLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(pWSkillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblAddAnother)
                            .addComponent(lblSpaceS, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pWSkillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pWSkillLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbbAddDMG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pWSkillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtAddDMG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbOperation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbChange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtShield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pWSkillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblCounter)
                                    .addComponent(counter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pWSkillLayout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(pWSkillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbbAnotherAddDMG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAnotherAddDMG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbbAnotherOperation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pSkillLayout = new javax.swing.GroupLayout(pSkill);
        pSkill.setLayout(pSkillLayout);
        pSkillLayout.setHorizontalGroup(
            pSkillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pSkillLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pWSkill, javax.swing.GroupLayout.PREFERRED_SIZE, 1305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );
        pSkillLayout.setVerticalGroup(
            pSkillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pSkillLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pWSkill, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                .addContainerGap())
        );

        pMain.add(pSkill, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 1380, 200));

        pAuraSeal.setBackground(new java.awt.Color(68, 108, 154));

        pSeals.setBackground(new java.awt.Color(119, 157, 202));
        pSeals.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(40, 80, 126)));

        lblSealList.setBackground(new java.awt.Color(90, 152, 210));
        lblSealList.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblSealList.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSealList.setText("Seals");

        NoSeal.setBackground(new java.awt.Color(119, 157, 202));
        Seals.add(NoSeal);
        NoSeal.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        NoSeal.setSelected(true);
        NoSeal.setText("None");
        NoSeal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkThunderstruckAuraItemStateChanged(evt);
            }
        });

        DMGSeal.setBackground(new java.awt.Color(119, 157, 202));
        Seals.add(DMGSeal);
        DMGSeal.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        DMGSeal.setText("DMG +50");
        DMGSeal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkThunderstruckAuraItemStateChanged(evt);
            }
        });

        DodgeSeal.setBackground(new java.awt.Color(119, 157, 202));
        Seals.add(DodgeSeal);
        DodgeSeal.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        DodgeSeal.setText("Dodge 1");
        DodgeSeal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkThunderstruckAuraItemStateChanged(evt);
            }
        });

        RageSeal.setBackground(new java.awt.Color(119, 157, 202));
        Seals.add(RageSeal);
        RageSeal.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        RageSeal.setText("Rage 20");
        RageSeal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkThunderstruckAuraItemStateChanged(evt);
            }
        });

        ResilienceSeal.setBackground(new java.awt.Color(119, 157, 202));
        Seals.add(ResilienceSeal);
        ResilienceSeal.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        ResilienceSeal.setText("Resilience 80");
        ResilienceSeal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkThunderstruckAuraItemStateChanged(evt);
            }
        });

        ShieldSeal.setBackground(new java.awt.Color(119, 157, 202));
        Seals.add(ShieldSeal);
        ShieldSeal.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        ShieldSeal.setText("Shield 400");
        ShieldSeal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkThunderstruckAuraItemStateChanged(evt);
            }
        });

        StenchSeal.setBackground(new java.awt.Color(119, 157, 202));
        Seals.add(StenchSeal);
        StenchSeal.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        StenchSeal.setText("Stench 30");
        StenchSeal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkThunderstruckAuraItemStateChanged(evt);
            }
        });

        StrengthSeal.setBackground(new java.awt.Color(119, 157, 202));
        Seals.add(StrengthSeal);
        StrengthSeal.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        StrengthSeal.setText("Strenght +60");
        StrengthSeal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkThunderstruckAuraItemStateChanged(evt);
            }
        });

        ThornSeal.setBackground(new java.awt.Color(119, 157, 202));
        Seals.add(ThornSeal);
        ThornSeal.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        ThornSeal.setText("Thorn 70");
        ThornSeal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkThunderstruckAuraItemStateChanged(evt);
            }
        });

        BlessingSeal.setBackground(new java.awt.Color(119, 157, 202));
        Seals.add(BlessingSeal);
        BlessingSeal.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        BlessingSeal.setText("Blessing 20");
        BlessingSeal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkThunderstruckAuraItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout pSealsLayout = new javax.swing.GroupLayout(pSeals);
        pSeals.setLayout(pSealsLayout);
        pSealsLayout.setHorizontalGroup(
            pSealsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pSealsLayout.createSequentialGroup()
                .addGroup(pSealsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pSealsLayout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(lblSealList, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pSealsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pSealsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(NoSeal)
                            .addComponent(DMGSeal)
                            .addComponent(DodgeSeal)
                            .addComponent(RageSeal))
                        .addGap(18, 18, 18)
                        .addGroup(pSealsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(ResilienceSeal)
                            .addComponent(ShieldSeal)
                            .addComponent(StenchSeal)
                            .addComponent(StrengthSeal))
                        .addGap(39, 39, 39)
                        .addGroup(pSealsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(BlessingSeal)
                            .addComponent(ThornSeal))))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        pSealsLayout.setVerticalGroup(
            pSealsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pSealsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSealList)
                .addGap(2, 2, 2)
                .addGroup(pSealsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pSealsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ResilienceSeal)
                        .addComponent(ThornSeal))
                    .addComponent(NoSeal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pSealsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pSealsLayout.createSequentialGroup()
                        .addComponent(DMGSeal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DodgeSeal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(RageSeal))
                    .addGroup(pSealsLayout.createSequentialGroup()
                        .addGroup(pSealsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ShieldSeal)
                            .addComponent(BlessingSeal))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(StenchSeal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(StrengthSeal)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pAuras.setBackground(new java.awt.Color(119, 157, 202));
        pAuras.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(40, 80, 126)));

        lblAuraList.setBackground(new java.awt.Color(90, 152, 210));
        lblAuraList.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblAuraList.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAuraList.setText("Auras");

        checkBerserkAura.setBackground(new java.awt.Color(119, 157, 202));
        checkBerserkAura.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        checkBerserkAura.setText("Berserk Aura 50%");
        checkBerserkAura.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkThunderstruckAuraItemStateChanged(evt);
            }
        });

        checkBonusDMGAura.setBackground(new java.awt.Color(119, 157, 202));
        checkBonusDMGAura.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        checkBonusDMGAura.setText("+DMG Aura 40%");
        checkBonusDMGAura.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkThunderstruckAuraItemStateChanged(evt);
            }
        });

        checkDodgeAura.setBackground(new java.awt.Color(119, 157, 202));
        checkDodgeAura.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        checkDodgeAura.setText("Dodge Aura 100%");
        checkDodgeAura.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkThunderstruckAuraItemStateChanged(evt);
            }
        });

        checkFrostbiteAura.setBackground(new java.awt.Color(119, 157, 202));
        checkFrostbiteAura.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        checkFrostbiteAura.setText("Frostbite Aura 50%");
        checkFrostbiteAura.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkThunderstruckAuraItemStateChanged(evt);
            }
        });

        checkHealAura.setBackground(new java.awt.Color(119, 157, 202));
        checkHealAura.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        checkHealAura.setText("Heal Aura 50%");
        checkHealAura.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkThunderstruckAuraItemStateChanged(evt);
            }
        });

        checkIceAura.setBackground(new java.awt.Color(119, 157, 202));
        checkIceAura.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        checkIceAura.setText("Ice Aura 100%");
        checkIceAura.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkThunderstruckAuraItemStateChanged(evt);
            }
        });

        checkMalusAura.setBackground(new java.awt.Color(119, 157, 202));
        checkMalusAura.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        checkMalusAura.setText("Malus DMG Aura 50%");
        checkMalusAura.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkThunderstruckAuraItemStateChanged(evt);
            }
        });

        checkRageAura.setBackground(new java.awt.Color(119, 157, 202));
        checkRageAura.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        checkRageAura.setText("Rage Aura 60%");
        checkRageAura.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkThunderstruckAuraItemStateChanged(evt);
            }
        });

        checkResilienceAura.setBackground(new java.awt.Color(119, 157, 202));
        checkResilienceAura.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        checkResilienceAura.setText("Resilience Aura 50%");
        checkResilienceAura.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkThunderstruckAuraItemStateChanged(evt);
            }
        });

        checkShieldAura.setBackground(new java.awt.Color(119, 157, 202));
        checkShieldAura.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        checkShieldAura.setText("Shield Aura 40%");
        checkShieldAura.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkThunderstruckAuraItemStateChanged(evt);
            }
        });

        checkStrAura.setBackground(new java.awt.Color(119, 157, 202));
        checkStrAura.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        checkStrAura.setText("Strenght Aura 30%");
        checkStrAura.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkThunderstruckAuraItemStateChanged(evt);
            }
        });

        checkThornAura.setBackground(new java.awt.Color(119, 157, 202));
        checkThornAura.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        checkThornAura.setText("Thorn Aura 30%");
        checkThornAura.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkThunderstruckAuraItemStateChanged(evt);
            }
        });

        checkThunderstruckAura.setBackground(new java.awt.Color(119, 157, 202));
        checkThunderstruckAura.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        checkThunderstruckAura.setText("Thunderstruck Aura 50%");
        checkThunderstruckAura.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkThunderstruckAuraItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout pAurasLayout = new javax.swing.GroupLayout(pAuras);
        pAuras.setLayout(pAurasLayout);
        pAurasLayout.setHorizontalGroup(
            pAurasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pAurasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pAurasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(checkBerserkAura)
                    .addComponent(checkBonusDMGAura)
                    .addComponent(checkDodgeAura)
                    .addComponent(checkFrostbiteAura)
                    .addComponent(checkHealAura))
                .addGap(18, 18, 18)
                .addGroup(pAurasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(checkIceAura)
                    .addComponent(checkMalusAura)
                    .addComponent(checkRageAura)
                    .addComponent(checkResilienceAura)
                    .addComponent(checkShieldAura)
                    .addComponent(lblAuraList, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pAurasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(checkThornAura)
                    .addComponent(checkThunderstruckAura)
                    .addComponent(checkStrAura))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pAurasLayout.setVerticalGroup(
            pAurasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pAurasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAuraList)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pAurasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pAurasLayout.createSequentialGroup()
                        .addComponent(checkBerserkAura)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(checkBonusDMGAura)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(checkDodgeAura)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(checkFrostbiteAura)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(checkHealAura))
                    .addGroup(pAurasLayout.createSequentialGroup()
                        .addGroup(pAurasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pAurasLayout.createSequentialGroup()
                                .addComponent(checkIceAura)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(checkMalusAura)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(checkRageAura))
                            .addGroup(pAurasLayout.createSequentialGroup()
                                .addComponent(checkStrAura)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(checkThornAura)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(checkThunderstruckAura)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(checkResilienceAura)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(checkShieldAura)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pAuraSealLayout = new javax.swing.GroupLayout(pAuraSeal);
        pAuraSeal.setLayout(pAuraSealLayout);
        pAuraSealLayout.setHorizontalGroup(
            pAuraSealLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pAuraSealLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pSeals, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pAuras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(503, Short.MAX_VALUE))
        );
        pAuraSealLayout.setVerticalGroup(
            pAuraSealLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pAuraSealLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pAuraSealLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pAuras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pSeals, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pMain.add(pAuraSeal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 1380, 200));

        pAssuming.setBackground(new java.awt.Color(68, 108, 154));

        pBlizz.setBackground(new java.awt.Color(119, 157, 202));

        lblBlizz.setText("Blizzard");

        lblBlizz1.setText("<html><center>How many Ice tokens Blizzard<br>will give after the 1st?</html>");

        lblBlizz2.setText("<html><center>How many Ice tokens Blizzard<br>will give after the 2nd?</html>");

        lblBlizz3.setText("<html><center>How many Ice tokens Blizzard<br>will give before the 3rd?</html>");

        javax.swing.GroupLayout pBlizzLayout = new javax.swing.GroupLayout(pBlizz);
        pBlizz.setLayout(pBlizzLayout);
        pBlizzLayout.setHorizontalGroup(
            pBlizzLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pBlizzLayout.createSequentialGroup()
                .addGroup(pBlizzLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pBlizzLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblBlizz1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(countBlizz1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pBlizzLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblBlizz2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(countBlizz2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pBlizzLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblBlizz3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(countBlizz3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pBlizzLayout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(lblBlizz)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pBlizzLayout.setVerticalGroup(
            pBlizzLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pBlizzLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBlizz)
                .addGap(18, 18, 18)
                .addGroup(pBlizzLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(countBlizz1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBlizz1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pBlizzLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(countBlizz2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBlizz2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pBlizzLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(countBlizz3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBlizz3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pHit.setBackground(new java.awt.Color(119, 157, 202));

        lblHit.setText("Berserk & Riposte");

        lblHit1.setText("How many times hit after 1st?");

        lblHit2.setText("How many times hit after 2nd?");

        lblHit3.setText("<html><center>How many times hit after 3rd?<br>No Sword Damage!</html>");

        lblHitS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHitS.setText("<html><center>How many times hit<br> by Swords?</html>");

        javax.swing.GroupLayout pHitLayout = new javax.swing.GroupLayout(pHit);
        pHit.setLayout(pHitLayout);
        pHitLayout.setHorizontalGroup(
            pHitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pHitLayout.createSequentialGroup()
                .addGroup(pHitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pHitLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pHitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(lblHit1)
                            .addComponent(lblHit2)
                            .addComponent(lblHit3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblHitS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pHitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(countHit1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(countHit2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(countHit3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(countHitS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pHitLayout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(lblHit)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pHitLayout.setVerticalGroup(
            pHitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pHitLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pHitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(countHit1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHit1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pHitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(countHit2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHit2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pHitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(countHit3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHit3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pHitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(countHitS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHitS))
                .addContainerGap())
        );

        pActiv.setBackground(new java.awt.Color(119, 157, 202));

        lblActiv.setText("Frostbite, Stench & Terror");

        lblActiv1.setText("<html><center>How many times the<br>opp activates the 1st?</html>");

        lblActiv2.setText("<html><center>How many times the<br>opp activates the 2nd?</html>");

        lblActiv3.setText("<html><center>How many times the<br>opp activates the 3rd?</html>");

        javax.swing.GroupLayout pActivLayout = new javax.swing.GroupLayout(pActiv);
        pActiv.setLayout(pActivLayout);
        pActivLayout.setHorizontalGroup(
            pActivLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pActivLayout.createSequentialGroup()
                .addGroup(pActivLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pActivLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pActivLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblActiv1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblActiv2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblActiv3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pActivLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(countActiv1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(countActiv2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(countActiv3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pActivLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(lblActiv)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pActivLayout.setVerticalGroup(
            pActivLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pActivLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblActiv)
                .addGap(18, 18, 18)
                .addGroup(pActivLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(countActiv1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblActiv1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pActivLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(countActiv2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblActiv2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pActivLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(countActiv3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblActiv3))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout pAssumingLayout = new javax.swing.GroupLayout(pAssuming);
        pAssuming.setLayout(pAssumingLayout);
        pAssumingLayout.setHorizontalGroup(
            pAssumingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pAssumingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pBlizz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pHit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pActiv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(660, Short.MAX_VALUE))
        );
        pAssumingLayout.setVerticalGroup(
            pAssumingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pAssumingLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pAssumingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pBlizz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pActiv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pHit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pMain.add(pAssuming, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 1380, 200));

        pBuffing.setBackground(new java.awt.Color(68, 108, 154));

        pTabs.setBackground(new java.awt.Color(92, 129, 172));

        pYouTab.setBackground(new java.awt.Color(119, 157, 202));

        lblYBerserk.setText("Berserk");

        txtYBerserk.setPreferredSize(new java.awt.Dimension(75, 25));
        txtYBerserk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        lblYBlessing.setText("Blessing");

        txtYBlessing.setPreferredSize(new java.awt.Dimension(75, 25));
        txtYBlessing.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        lblYBlizzard.setText("Blizzard");

        txtYBlizzard.setPreferredSize(new java.awt.Dimension(75, 25));
        txtYBlizzard.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        lblYBulwark.setText("Bulwark");

        txtYBulwark.setPreferredSize(new java.awt.Dimension(75, 25));
        txtYBulwark.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        lblYCritical.setText("Critical");

        txtYCritical.setPreferredSize(new java.awt.Dimension(75, 25));
        txtYCritical.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        lblYDMGBonus.setText("+ DMG");

        txtYDMGBonus.setPreferredSize(new java.awt.Dimension(75, 25));
        txtYDMGBonus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        lblYDMGMalus.setText("- DMG");

        txtYDMGMalus.setPreferredSize(new java.awt.Dimension(75, 25));
        txtYDMGMalus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        lblYDodge.setText("Dodge");

        txtYDodge.setPreferredSize(new java.awt.Dimension(75, 25));
        txtYDodge.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        lblYEclipse.setText("Eclipse");

        txtYEclipse.setPreferredSize(new java.awt.Dimension(75, 25));
        txtYEclipse.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        lblYFrostbite.setText("Frostbite");

        txtYFrostbite.setPreferredSize(new java.awt.Dimension(75, 25));
        txtYFrostbite.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        lblYIce.setText("Ice");

        txtYIce.setPreferredSize(new java.awt.Dimension(75, 25));
        txtYIce.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        lblYPowder.setText("Powder");

        txtYPowder.setPreferredSize(new java.awt.Dimension(75, 25));
        txtYPowder.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        lblYRage.setText("Rage");

        txtYRage.setPreferredSize(new java.awt.Dimension(75, 25));
        txtYRage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        lblYResilience.setText("Resilience");

        txtYResilience.setPreferredSize(new java.awt.Dimension(75, 25));
        txtYResilience.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        lblYRiposte.setText("Riposte");

        txtYRiposte.setPreferredSize(new java.awt.Dimension(75, 25));
        txtYRiposte.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        lblYRune.setText("Rune");

        txtYRune.setPreferredSize(new java.awt.Dimension(75, 25));
        txtYRune.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        lblYScarab.setText("Scarab");

        txtYScarab.setPreferredSize(new java.awt.Dimension(75, 25));
        txtYScarab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        lblYShield.setText("Shield");

        txtYShield.setPreferredSize(new java.awt.Dimension(75, 25));
        txtYShield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        lblYStench.setText("Stench");

        txtYStench.setPreferredSize(new java.awt.Dimension(75, 25));
        txtYStench.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        lblYSTRBonus.setText("+ STR");

        txtYSTRBonus.setPreferredSize(new java.awt.Dimension(75, 25));
        txtYSTRBonus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        lblYSTRMalus.setText("- STR");

        txtYSTRMalus.setPreferredSize(new java.awt.Dimension(75, 25));
        txtYSTRMalus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        lblYTerror.setText("Terror");

        txtYTerror.setPreferredSize(new java.awt.Dimension(75, 25));
        txtYTerror.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        lblYThorn.setText("Thorn");

        txtYThorn.setPreferredSize(new java.awt.Dimension(75, 25));
        txtYThorn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        lblYThunderstruck.setText("Tstruck");

        txtYThunderstruck.setPreferredSize(new java.awt.Dimension(75, 25));
        txtYThunderstruck.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        txtYStorm.setPreferredSize(new java.awt.Dimension(75, 25));
        txtYStorm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtYStormtxtStrKeyTyped(evt);
            }
        });

        lblYSTRMalus1.setText("Storm");

        javax.swing.GroupLayout pYouTabLayout = new javax.swing.GroupLayout(pYouTab);
        pYouTab.setLayout(pYouTabLayout);
        pYouTabLayout.setHorizontalGroup(
            pYouTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pYouTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pYouTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblYBerserk, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblYBlessing, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblYBlizzard, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblYBulwark, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pYouTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtYBulwark, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtYBlizzard, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtYBlessing, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtYBerserk, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pYouTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblYCritical, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblYDMGBonus, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblYDMGMalus, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblYDodge, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pYouTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtYCritical, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtYDMGBonus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtYDMGMalus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtYDodge, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pYouTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblYEclipse, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblYFrostbite, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblYIce, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblYPowder, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pYouTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtYEclipse, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtYFrostbite, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtYIce, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtYPowder, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pYouTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblYRage, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblYResilience, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblYRiposte, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblYRune, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pYouTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtYRage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtYResilience, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtYRiposte, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtYRune, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pYouTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblYScarab, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblYShield, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblYStench, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblYSTRBonus, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pYouTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtYScarab, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtYShield, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtYStench, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtYSTRBonus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pYouTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblYSTRMalus, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblYTerror, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblYThorn, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblYThunderstruck, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pYouTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtYSTRMalus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtYTerror, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtYThorn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtYThunderstruck, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblYSTRMalus1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtYStorm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pYouTabLayout.setVerticalGroup(
            pYouTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pYouTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pYouTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pYouTabLayout.createSequentialGroup()
                        .addGroup(pYouTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pYouTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblYSTRMalus1)
                                .addComponent(txtYStorm, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pYouTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblYSTRMalus)
                                .addComponent(txtYSTRMalus, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pYouTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblYTerror)
                            .addComponent(txtYTerror, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pYouTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblYThorn)
                            .addComponent(txtYThorn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pYouTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblYThunderstruck)
                            .addComponent(txtYThunderstruck, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pYouTabLayout.createSequentialGroup()
                        .addGroup(pYouTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblYScarab)
                            .addComponent(txtYScarab, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pYouTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblYShield)
                            .addComponent(txtYShield, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pYouTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblYStench)
                            .addComponent(txtYStench, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pYouTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblYSTRBonus)
                            .addComponent(txtYSTRBonus, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pYouTabLayout.createSequentialGroup()
                        .addGroup(pYouTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblYRage)
                            .addComponent(txtYRage, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pYouTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblYResilience)
                            .addComponent(txtYResilience, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pYouTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblYRiposte)
                            .addComponent(txtYRiposte, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pYouTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblYRune)
                            .addComponent(txtYRune, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pYouTabLayout.createSequentialGroup()
                        .addGroup(pYouTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblYEclipse)
                            .addComponent(txtYEclipse, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pYouTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblYFrostbite)
                            .addComponent(txtYFrostbite, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pYouTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblYIce)
                            .addComponent(txtYIce, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pYouTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblYPowder)
                            .addComponent(txtYPowder, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pYouTabLayout.createSequentialGroup()
                        .addGroup(pYouTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblYCritical)
                            .addComponent(txtYCritical, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pYouTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblYDMGBonus)
                            .addComponent(txtYDMGBonus, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pYouTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblYDMGMalus)
                            .addComponent(txtYDMGMalus, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pYouTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblYDodge)
                            .addComponent(txtYDodge, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pYouTabLayout.createSequentialGroup()
                        .addGroup(pYouTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblYBerserk)
                            .addComponent(txtYBerserk, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pYouTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblYBlessing)
                            .addComponent(txtYBlessing, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pYouTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblYBlizzard)
                            .addComponent(txtYBlizzard, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pYouTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblYBulwark)
                            .addComponent(txtYBulwark, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pTabs.addTab("Your Alterarions", pYouTab);

        pOppTab.setBackground(new java.awt.Color(92, 129, 172));

        lblOBerserk.setForeground(new java.awt.Color(255, 255, 255));
        lblOBerserk.setText("Berserk");

        txtOBerserk.setPreferredSize(new java.awt.Dimension(75, 25));
        txtOBerserk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        lblOBlessing.setForeground(new java.awt.Color(255, 255, 255));
        lblOBlessing.setText("Blessing");

        txtOBlessing.setPreferredSize(new java.awt.Dimension(75, 25));
        txtOBlessing.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        lblOBlizzard.setForeground(new java.awt.Color(255, 255, 255));
        lblOBlizzard.setText("Blizzard");

        txtOBlizzard.setPreferredSize(new java.awt.Dimension(75, 25));
        txtOBlizzard.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        lblOBulwark.setForeground(new java.awt.Color(255, 255, 255));
        lblOBulwark.setText("Bulwark");

        txtOBulwark.setPreferredSize(new java.awt.Dimension(75, 25));
        txtOBulwark.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        lblOCritical.setForeground(new java.awt.Color(255, 255, 255));
        lblOCritical.setText("Critical");

        txtOCritical.setPreferredSize(new java.awt.Dimension(75, 25));
        txtOCritical.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        lblODMGBonus.setForeground(new java.awt.Color(255, 255, 255));
        lblODMGBonus.setText("+ DMG");

        txtODMGBonus.setPreferredSize(new java.awt.Dimension(75, 25));
        txtODMGBonus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        txtODMGMalus.setPreferredSize(new java.awt.Dimension(75, 25));
        txtODMGMalus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        lblODMGMalus.setForeground(new java.awt.Color(255, 255, 255));
        lblODMGMalus.setText("- DMG");

        lblODodge.setForeground(new java.awt.Color(255, 255, 255));
        lblODodge.setText("Dodge");

        txtODodge.setPreferredSize(new java.awt.Dimension(75, 25));
        txtODodge.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        lblOEclipse.setForeground(new java.awt.Color(255, 255, 255));
        lblOEclipse.setText("Eclipse");

        txtOEclipse.setPreferredSize(new java.awt.Dimension(75, 25));
        txtOEclipse.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        lblOFrostbite.setForeground(new java.awt.Color(255, 255, 255));
        lblOFrostbite.setText("Frostbite");

        txtOFrostbite.setPreferredSize(new java.awt.Dimension(75, 25));
        txtOFrostbite.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        lblOIce.setForeground(new java.awt.Color(255, 255, 255));
        lblOIce.setText("Ice");

        txtOIce.setPreferredSize(new java.awt.Dimension(75, 25));
        txtOIce.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        lblOPowder.setForeground(new java.awt.Color(255, 255, 255));
        lblOPowder.setText("Powder");

        txtOPowder.setPreferredSize(new java.awt.Dimension(75, 25));
        txtOPowder.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        lblORage.setForeground(new java.awt.Color(255, 255, 255));
        lblORage.setText("Rage");

        txtORage.setPreferredSize(new java.awt.Dimension(75, 25));
        txtORage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        lblOResilience.setForeground(new java.awt.Color(255, 255, 255));
        lblOResilience.setText("Resilience");

        txtOResilience.setPreferredSize(new java.awt.Dimension(75, 25));
        txtOResilience.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        lblORiposte.setForeground(new java.awt.Color(255, 255, 255));
        lblORiposte.setText("Riposte");

        txtORiposte.setPreferredSize(new java.awt.Dimension(75, 25));
        txtORiposte.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        lblORune.setForeground(new java.awt.Color(255, 255, 255));
        lblORune.setText("Rune");

        txtORune.setPreferredSize(new java.awt.Dimension(75, 25));
        txtORune.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        lblOScarab.setForeground(new java.awt.Color(255, 255, 255));
        lblOScarab.setText("Scarab");

        txtOScarab.setPreferredSize(new java.awt.Dimension(75, 25));
        txtOScarab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        lblOShield.setForeground(new java.awt.Color(255, 255, 255));
        lblOShield.setText("Shield");

        txtOShield.setPreferredSize(new java.awt.Dimension(75, 25));
        txtOShield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        lblOStench.setForeground(new java.awt.Color(255, 255, 255));
        lblOStench.setText("Stench");

        txtOStench.setPreferredSize(new java.awt.Dimension(75, 25));
        txtOStench.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        lblOSTRBonus.setForeground(new java.awt.Color(255, 255, 255));
        lblOSTRBonus.setText("+ STR");

        txtOSTRBonus.setPreferredSize(new java.awt.Dimension(75, 25));
        txtOSTRBonus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        lblOSTRMalus.setForeground(new java.awt.Color(255, 255, 255));
        lblOSTRMalus.setText("- STR");

        txtOSTRMalus.setPreferredSize(new java.awt.Dimension(75, 25));
        txtOSTRMalus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        lblOTerror.setForeground(new java.awt.Color(255, 255, 255));
        lblOTerror.setText("Terror");

        txtOTerror.setPreferredSize(new java.awt.Dimension(75, 25));
        txtOTerror.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        lblOThorn.setForeground(new java.awt.Color(255, 255, 255));
        lblOThorn.setText("Thorn");

        txtOThorn.setPreferredSize(new java.awt.Dimension(75, 25));
        txtOThorn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        lblOThunderstruck.setForeground(new java.awt.Color(255, 255, 255));
        lblOThunderstruck.setText("Tstruck");

        txtOThunderstruck.setPreferredSize(new java.awt.Dimension(75, 25));
        txtOThunderstruck.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        txtOStorm.setPreferredSize(new java.awt.Dimension(75, 25));
        txtOStorm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtOStormtxtStrKeyTyped(evt);
            }
        });

        lblOSTRMalus1.setForeground(new java.awt.Color(255, 255, 255));
        lblOSTRMalus1.setText("Storm");

        javax.swing.GroupLayout pOppTabLayout = new javax.swing.GroupLayout(pOppTab);
        pOppTab.setLayout(pOppTabLayout);
        pOppTabLayout.setHorizontalGroup(
            pOppTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pOppTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pOppTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblOBerserk, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblOBlessing, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblOBlizzard, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblOBulwark, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pOppTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtOBulwark, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOBlizzard, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOBlessing, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOBerserk, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pOppTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblOCritical, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblODMGBonus, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblODMGMalus, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblODodge, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pOppTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtOCritical, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtODMGBonus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtODMGMalus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtODodge, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pOppTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblOEclipse, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblOFrostbite, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblOIce, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblOPowder, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pOppTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtOEclipse, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOFrostbite, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOIce, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOPowder, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pOppTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblORage, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblOResilience, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblORiposte, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblORune, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pOppTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtORage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOResilience, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtORiposte, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtORune, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pOppTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblOScarab, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblOShield, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblOStench, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblOSTRBonus, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pOppTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtOScarab, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOShield, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOStench, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOSTRBonus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pOppTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblOSTRMalus, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblOTerror, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblOThorn, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblOThunderstruck, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pOppTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtOSTRMalus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOTerror, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOThorn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOThunderstruck, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblOSTRMalus1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtOStorm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        pOppTabLayout.setVerticalGroup(
            pOppTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pOppTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pOppTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pOppTabLayout.createSequentialGroup()
                        .addGroup(pOppTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pOppTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblOSTRMalus1)
                                .addComponent(txtOStorm, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pOppTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblOSTRMalus)
                                .addComponent(txtOSTRMalus, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pOppTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblOTerror)
                            .addComponent(txtOTerror, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pOppTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblOThorn)
                            .addComponent(txtOThorn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pOppTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblOThunderstruck)
                            .addComponent(txtOThunderstruck, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pOppTabLayout.createSequentialGroup()
                        .addGroup(pOppTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblOScarab)
                            .addComponent(txtOScarab, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pOppTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblOShield)
                            .addComponent(txtOShield, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pOppTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblOStench)
                            .addComponent(txtOStench, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pOppTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblOSTRBonus)
                            .addComponent(txtOSTRBonus, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pOppTabLayout.createSequentialGroup()
                        .addGroup(pOppTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblORage)
                            .addComponent(txtORage, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pOppTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblOResilience)
                            .addComponent(txtOResilience, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pOppTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblORiposte)
                            .addComponent(txtORiposte, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pOppTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblORune)
                            .addComponent(txtORune, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pOppTabLayout.createSequentialGroup()
                        .addGroup(pOppTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblOEclipse)
                            .addComponent(txtOEclipse, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pOppTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblOFrostbite)
                            .addComponent(txtOFrostbite, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pOppTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblOIce)
                            .addComponent(txtOIce, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pOppTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblOPowder)
                            .addComponent(txtOPowder, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pOppTabLayout.createSequentialGroup()
                        .addGroup(pOppTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblOCritical)
                            .addComponent(txtOCritical, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pOppTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblODMGBonus)
                            .addComponent(txtODMGBonus, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pOppTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblODMGMalus)
                            .addComponent(txtODMGMalus, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pOppTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblODodge)
                            .addComponent(txtODodge, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pOppTabLayout.createSequentialGroup()
                        .addGroup(pOppTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblOBerserk)
                            .addComponent(txtOBerserk, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pOppTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblOBlessing)
                            .addComponent(txtOBlessing, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pOppTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblOBlizzard)
                            .addComponent(txtOBlizzard, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pOppTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblOBulwark)
                            .addComponent(txtOBulwark, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pTabs.addTab("Opponent's Alterations", pOppTab);

        javax.swing.GroupLayout pBuffingLayout = new javax.swing.GroupLayout(pBuffing);
        pBuffing.setLayout(pBuffingLayout);
        pBuffingLayout.setHorizontalGroup(
            pBuffingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pBuffingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pTabs, javax.swing.GroupLayout.PREFERRED_SIZE, 944, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(426, Short.MAX_VALUE))
        );
        pBuffingLayout.setVerticalGroup(
            pBuffingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pBuffingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pTabs)
                .addContainerGap())
        );

        pMain.add(pBuffing, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 1380, 200));

        pChoose.setBackground(new java.awt.Color(119, 157, 202));
        pChoose.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pThird.setBackground(new java.awt.Color(119, 157, 202));
        pThird.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(40, 80, 126)));
        pThird.setPreferredSize(new java.awt.Dimension(511, 100));

        pT1.setBackground(new java.awt.Color(79, 116, 159));
        pT1.setPreferredSize(new java.awt.Dimension(150, 50));
        pT1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pT1MouseClicked(evt);
            }
        });

        lblCM3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCM3.setForeground(new java.awt.Color(119, 155, 197));
        lblCM3.setText("CLICK ME");

        lblT1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        javax.swing.GroupLayout pT1Layout = new javax.swing.GroupLayout(pT1);
        pT1.setLayout(pT1Layout);
        pT1Layout.setHorizontalGroup(
            pT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pT1Layout.createSequentialGroup()
                .addComponent(lblCM3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 129, Short.MAX_VALUE))
            .addGroup(pT1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblT1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pT1Layout.setVerticalGroup(
            pT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pT1Layout.createSequentialGroup()
                .addComponent(lblCM3)
                .addGap(18, 18, 18)
                .addComponent(lblT1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pT2.setBackground(new java.awt.Color(79, 116, 159));
        pT2.setPreferredSize(new java.awt.Dimension(150, 50));
        pT2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pT2MouseClicked(evt);
            }
        });

        lblT2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        lblCM4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCM4.setForeground(new java.awt.Color(119, 155, 197));
        lblCM4.setText("CLICK ME");

        javax.swing.GroupLayout pT2Layout = new javax.swing.GroupLayout(pT2);
        pT2.setLayout(pT2Layout);
        pT2Layout.setHorizontalGroup(
            pT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pT2Layout.createSequentialGroup()
                .addComponent(lblCM4, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 129, Short.MAX_VALUE))
            .addGroup(pT2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblT2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pT2Layout.setVerticalGroup(
            pT2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pT2Layout.createSequentialGroup()
                .addComponent(lblCM4)
                .addGap(18, 18, 18)
                .addComponent(lblT2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pT3.setBackground(new java.awt.Color(79, 116, 159));
        pT3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pT3MouseClicked(evt);
            }
        });

        lblT3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        lblCM5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCM5.setForeground(new java.awt.Color(119, 155, 197));
        lblCM5.setText("CLICK ME");

        javax.swing.GroupLayout pT3Layout = new javax.swing.GroupLayout(pT3);
        pT3.setLayout(pT3Layout);
        pT3Layout.setHorizontalGroup(
            pT3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pT3Layout.createSequentialGroup()
                .addComponent(lblCM5, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 129, Short.MAX_VALUE))
            .addGroup(pT3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblT3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pT3Layout.setVerticalGroup(
            pT3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pT3Layout.createSequentialGroup()
                .addComponent(lblCM5)
                .addGap(18, 18, 18)
                .addComponent(lblT3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        btnTMinus.setBackground(new java.awt.Color(21, 58, 100));
        btnTMinus.setForeground(new java.awt.Color(255, 255, 255));
        btnTMinus.setText("-");
        btnTMinus.setEnabled(false);
        btnTMinus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTMinusActionPerformed(evt);
            }
        });

        btnTPlus.setBackground(new java.awt.Color(21, 58, 100));
        btnTPlus.setForeground(new java.awt.Color(255, 255, 255));
        btnTPlus.setText("+");
        btnTPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTPlusActionPerformed(evt);
            }
        });

        TD1.setBackground(new java.awt.Color(26, 50, 78));
        TD1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TD2ItemStateChanged(evt);
            }
        });
        TD1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TD1KeyTyped(evt);
            }
        });

        TD2.setBackground(new java.awt.Color(26, 50, 78));
        TD2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TD2ItemStateChanged(evt);
            }
        });
        TD2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TD2KeyTyped(evt);
            }
        });

        TD3.setBackground(new java.awt.Color(26, 50, 78));
        TD3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TD2ItemStateChanged(evt);
            }
        });
        TD3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TD3KeyTyped(evt);
            }
        });

        TD4.setBackground(new java.awt.Color(26, 50, 78));
        TD4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TD2ItemStateChanged(evt);
            }
        });
        TD4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TD4KeyTyped(evt);
            }
        });

        lblTtoM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/pArrowDown.png"))); // NOI18N
        lblTtoM.setPreferredSize(new java.awt.Dimension(25, 25));
        lblTtoM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTtoMMouseClicked(evt);
            }
        });

        TD5.setBackground(new java.awt.Color(26, 50, 78));
        TD5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TD2ItemStateChanged(evt);
            }
        });
        TD5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TD5KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout pThirdLayout = new javax.swing.GroupLayout(pThird);
        pThird.setLayout(pThirdLayout);
        pThirdLayout.setHorizontalGroup(
            pThirdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pThirdLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pThirdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pThirdLayout.createSequentialGroup()
                        .addComponent(TD1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TD2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TD3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pThirdLayout.createSequentialGroup()
                        .addComponent(TD4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TD5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(pT1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pT2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pT3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pThirdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTMinus, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pThirdLayout.createSequentialGroup()
                        .addComponent(btnTPlus, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTtoM, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(214, 214, 214))
        );
        pThirdLayout.setVerticalGroup(
            pThirdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pThirdLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pThirdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pT1, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pThirdLayout.createSequentialGroup()
                        .addGroup(pThirdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pThirdLayout.createSequentialGroup()
                                .addGroup(pThirdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(TD1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TD2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TD3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pThirdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(TD4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(TD5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pThirdLayout.createSequentialGroup()
                                .addGroup(pThirdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnTPlus)
                                    .addComponent(lblTtoM, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTMinus, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pThirdLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 12, Short.MAX_VALUE))
                    .addComponent(pT2, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                    .addComponent(pT3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pChoose.add(pThird, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, 1080, 90));

        pSecond.setBackground(new java.awt.Color(119, 157, 202));
        pSecond.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(40, 80, 126)));
        pSecond.setPreferredSize(new java.awt.Dimension(511, 100));

        pM1.setBackground(new java.awt.Color(79, 116, 159));
        pM1.setPreferredSize(new java.awt.Dimension(150, 50));
        pM1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pM1MouseClicked(evt);
            }
        });

        lblM1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        lblCM6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCM6.setForeground(new java.awt.Color(119, 155, 197));
        lblCM6.setText("CLICK ME");

        javax.swing.GroupLayout pM1Layout = new javax.swing.GroupLayout(pM1);
        pM1.setLayout(pM1Layout);
        pM1Layout.setHorizontalGroup(
            pM1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pM1Layout.createSequentialGroup()
                .addComponent(lblCM6, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 129, Short.MAX_VALUE))
            .addGroup(pM1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblM1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pM1Layout.setVerticalGroup(
            pM1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pM1Layout.createSequentialGroup()
                .addComponent(lblCM6)
                .addGap(18, 18, 18)
                .addComponent(lblM1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pM2.setBackground(new java.awt.Color(79, 116, 159));
        pM2.setPreferredSize(new java.awt.Dimension(150, 50));
        pM2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pM2MouseClicked(evt);
            }
        });

        lblM2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        lblCM7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCM7.setForeground(new java.awt.Color(119, 155, 197));
        lblCM7.setText("CLICK ME");

        javax.swing.GroupLayout pM2Layout = new javax.swing.GroupLayout(pM2);
        pM2.setLayout(pM2Layout);
        pM2Layout.setHorizontalGroup(
            pM2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pM2Layout.createSequentialGroup()
                .addComponent(lblCM7, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 129, Short.MAX_VALUE))
            .addGroup(pM2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblM2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pM2Layout.setVerticalGroup(
            pM2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pM2Layout.createSequentialGroup()
                .addComponent(lblCM7)
                .addGap(18, 18, 18)
                .addComponent(lblM2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pM3.setBackground(new java.awt.Color(79, 116, 159));
        pM3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pM3MouseClicked(evt);
            }
        });

        lblM3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        lblCM8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCM8.setForeground(new java.awt.Color(119, 155, 197));
        lblCM8.setText("CLICK ME");

        javax.swing.GroupLayout pM3Layout = new javax.swing.GroupLayout(pM3);
        pM3.setLayout(pM3Layout);
        pM3Layout.setHorizontalGroup(
            pM3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pM3Layout.createSequentialGroup()
                .addComponent(lblCM8, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 129, Short.MAX_VALUE))
            .addGroup(pM3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblM3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pM3Layout.setVerticalGroup(
            pM3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pM3Layout.createSequentialGroup()
                .addComponent(lblCM8)
                .addGap(18, 18, 18)
                .addComponent(lblM3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        btnMMinus.setBackground(new java.awt.Color(21, 58, 100));
        btnMMinus.setForeground(new java.awt.Color(255, 255, 255));
        btnMMinus.setText("-");
        btnMMinus.setEnabled(false);
        btnMMinus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMMinusActionPerformed(evt);
            }
        });

        btnMPlus.setBackground(new java.awt.Color(21, 58, 100));
        btnMPlus.setForeground(new java.awt.Color(255, 255, 255));
        btnMPlus.setText("+");
        btnMPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMPlusActionPerformed(evt);
            }
        });

        MD1.setBackground(new java.awt.Color(26, 50, 78));
        MD1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                MD4ItemStateChanged(evt);
            }
        });
        MD1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                MD1KeyTyped(evt);
            }
        });

        MD2.setBackground(new java.awt.Color(26, 50, 78));
        MD2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                MD4ItemStateChanged(evt);
            }
        });
        MD2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                MD2KeyTyped(evt);
            }
        });

        MD3.setBackground(new java.awt.Color(26, 50, 78));
        MD3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                MD4ItemStateChanged(evt);
            }
        });
        MD3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                MD3KeyTyped(evt);
            }
        });

        MD4.setBackground(new java.awt.Color(26, 50, 78));
        MD4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                MD4ItemStateChanged(evt);
            }
        });
        MD4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                MD4KeyTyped(evt);
            }
        });

        lblMtoT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/pArrowUp.png"))); // NOI18N
        lblMtoT.setPreferredSize(new java.awt.Dimension(25, 25));
        lblMtoT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMtoTMouseClicked(evt);
            }
        });

        lblMtoB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/pArrowDown.png"))); // NOI18N
        lblMtoB.setPreferredSize(new java.awt.Dimension(25, 25));
        lblMtoB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMtoBMouseClicked(evt);
            }
        });

        MD5.setBackground(new java.awt.Color(26, 50, 78));
        MD5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                MD4ItemStateChanged(evt);
            }
        });
        MD5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                MD5KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout pSecondLayout = new javax.swing.GroupLayout(pSecond);
        pSecond.setLayout(pSecondLayout);
        pSecondLayout.setHorizontalGroup(
            pSecondLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pSecondLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pSecondLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pSecondLayout.createSequentialGroup()
                        .addComponent(MD1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MD2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MD3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pSecondLayout.createSequentialGroup()
                        .addComponent(MD4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MD5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(pM1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pM2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pM3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pSecondLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMMinus, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pSecondLayout.createSequentialGroup()
                        .addComponent(btnMPlus, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblMtoT, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMtoB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(219, Short.MAX_VALUE))
        );
        pSecondLayout.setVerticalGroup(
            pSecondLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pSecondLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pSecondLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pM1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                    .addComponent(pM2, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                    .addComponent(pM3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pSecondLayout.createSequentialGroup()
                        .addGroup(pSecondLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pSecondLayout.createSequentialGroup()
                                .addGroup(pSecondLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(MD1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(MD2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(MD3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pSecondLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(MD4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(MD5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pSecondLayout.createSequentialGroup()
                                .addGroup(pSecondLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnMPlus, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblMtoB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblMtoT, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnMMinus, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 12, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pChoose.add(pSecond, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 140, 1080, 90));

        pFirst.setBackground(new java.awt.Color(119, 157, 202));
        pFirst.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(40, 80, 126)));
        pFirst.setPreferredSize(new java.awt.Dimension(511, 100));

        pB1.setBackground(new java.awt.Color(79, 116, 159));
        pB1.setPreferredSize(new java.awt.Dimension(150, 50));
        pB1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pB1MouseClicked(evt);
            }
        });

        lblB1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        lblCM9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCM9.setForeground(new java.awt.Color(119, 155, 197));
        lblCM9.setText("CLICK ME");

        javax.swing.GroupLayout pB1Layout = new javax.swing.GroupLayout(pB1);
        pB1.setLayout(pB1Layout);
        pB1Layout.setHorizontalGroup(
            pB1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pB1Layout.createSequentialGroup()
                .addComponent(lblCM9, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 129, Short.MAX_VALUE))
            .addGroup(pB1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblB1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pB1Layout.setVerticalGroup(
            pB1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pB1Layout.createSequentialGroup()
                .addComponent(lblCM9)
                .addGap(18, 18, 18)
                .addComponent(lblB1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pB2.setBackground(new java.awt.Color(79, 116, 159));
        pB2.setPreferredSize(new java.awt.Dimension(150, 50));
        pB2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pB2MouseClicked(evt);
            }
        });

        lblB2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        lblCM10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCM10.setForeground(new java.awt.Color(119, 155, 197));
        lblCM10.setText("CLICK ME");

        javax.swing.GroupLayout pB2Layout = new javax.swing.GroupLayout(pB2);
        pB2.setLayout(pB2Layout);
        pB2Layout.setHorizontalGroup(
            pB2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pB2Layout.createSequentialGroup()
                .addComponent(lblCM10, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 129, Short.MAX_VALUE))
            .addGroup(pB2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblB2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pB2Layout.setVerticalGroup(
            pB2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pB2Layout.createSequentialGroup()
                .addComponent(lblCM10)
                .addGap(18, 18, 18)
                .addComponent(lblB2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pB3.setBackground(new java.awt.Color(79, 116, 159));
        pB3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pB3MouseClicked(evt);
            }
        });

        lblB3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        lblCM11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCM11.setForeground(new java.awt.Color(119, 155, 197));
        lblCM11.setText("CLICK ME");

        javax.swing.GroupLayout pB3Layout = new javax.swing.GroupLayout(pB3);
        pB3.setLayout(pB3Layout);
        pB3Layout.setHorizontalGroup(
            pB3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pB3Layout.createSequentialGroup()
                .addComponent(lblCM11, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 129, Short.MAX_VALUE))
            .addGroup(pB3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblB3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pB3Layout.setVerticalGroup(
            pB3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pB3Layout.createSequentialGroup()
                .addComponent(lblCM11)
                .addGap(18, 18, 18)
                .addComponent(lblB3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        btnBMinus.setBackground(new java.awt.Color(21, 58, 100));
        btnBMinus.setForeground(new java.awt.Color(255, 255, 255));
        btnBMinus.setText("-");
        btnBMinus.setEnabled(false);
        btnBMinus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBMinusActionPerformed(evt);
            }
        });

        btnBPlus.setBackground(new java.awt.Color(21, 58, 100));
        btnBPlus.setForeground(new java.awt.Color(255, 255, 255));
        btnBPlus.setText("+");
        btnBPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBPlusActionPerformed(evt);
            }
        });

        BD1.setBackground(new java.awt.Color(26, 50, 78));
        BD1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                BD4ItemStateChanged(evt);
            }
        });
        BD1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                BD1KeyTyped(evt);
            }
        });

        BD2.setBackground(new java.awt.Color(26, 50, 78));
        BD2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                BD4ItemStateChanged(evt);
            }
        });
        BD2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                BD2KeyTyped(evt);
            }
        });

        BD3.setBackground(new java.awt.Color(26, 50, 78));
        BD3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                BD4ItemStateChanged(evt);
            }
        });
        BD3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                BD3KeyTyped(evt);
            }
        });

        BD4.setBackground(new java.awt.Color(26, 50, 78));
        BD4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                BD4ItemStateChanged(evt);
            }
        });
        BD4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                BD4KeyTyped(evt);
            }
        });

        lblBtoM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/pArrowUp.png"))); // NOI18N
        lblBtoM.setPreferredSize(new java.awt.Dimension(25, 25));
        lblBtoM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBtoMMouseClicked(evt);
            }
        });

        BD5.setBackground(new java.awt.Color(26, 50, 78));
        BD5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                BD4ItemStateChanged(evt);
            }
        });
        BD5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                BD5KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout pFirstLayout = new javax.swing.GroupLayout(pFirst);
        pFirst.setLayout(pFirstLayout);
        pFirstLayout.setHorizontalGroup(
            pFirstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pFirstLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pFirstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pFirstLayout.createSequentialGroup()
                        .addComponent(BD4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BD5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pFirstLayout.createSequentialGroup()
                        .addComponent(BD1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BD2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BD3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(pB1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pB2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pB3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pFirstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBMinus, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pFirstLayout.createSequentialGroup()
                        .addComponent(btnBPlus, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblBtoM, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(342, 342, 342))
        );
        pFirstLayout.setVerticalGroup(
            pFirstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pFirstLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pFirstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pB3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pB1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                    .addComponent(pB2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                    .addGroup(pFirstLayout.createSequentialGroup()
                        .addGroup(pFirstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pFirstLayout.createSequentialGroup()
                                .addGroup(pFirstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(BD1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(BD2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(BD3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pFirstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(BD4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(BD5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pFirstLayout.createSequentialGroup()
                                .addGroup(pFirstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnBPlus, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblBtoM, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBMinus, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 12, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pChoose.add(pFirst, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 230, 1080, 90));

        pYou.setBackground(new java.awt.Color(119, 157, 202));
        pYou.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(40, 80, 126)));
        pYou.setPreferredSize(new java.awt.Dimension(511, 100));

        lblYou.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblYou.setText("Your Hero");

        lblSTR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/DS_20.png"))); // NOI18N
        lblSTR.setText(" = ");
        lblSTR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(95, 133, 179)));

        txtStr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStrActionPerformed(evt);
            }
        });
        txtStr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        cbbGuild.setMaximumRowCount(20);
        cbbGuild.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbGuildItemStateChanged(evt);
            }
        });

        cbbRace.setMaximumRowCount(20);
        cbbRace.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbRaceItemStateChanged(evt);
            }
        });

        cbbClass.setMaximumRowCount(20);
        cbbClass.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbClassItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout pYouLayout = new javax.swing.GroupLayout(pYou);
        pYou.setLayout(pYouLayout);
        pYouLayout.setHorizontalGroup(
            pYouLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pYouLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblYou)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                .addComponent(lblSTR)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtStr, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cbbGuild, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbbRace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbbClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pYouLayout.setVerticalGroup(
            pYouLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pYouLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pYouLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblYou)
                    .addComponent(lblSTR)
                    .addComponent(txtStr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbGuild, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbRace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );

        pChoose.add(pYou, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 50));

        pOpp.setBackground(new java.awt.Color(119, 157, 202));
        pOpp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(40, 80, 126)));
        pOpp.setPreferredSize(new java.awt.Dimension(511, 100));

        lblOpp.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblOpp.setText("Your Opponent");

        lblOppSTR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/DS_20.png"))); // NOI18N
        lblOppSTR.setText(" = ");
        lblOppSTR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(95, 133, 179)));

        txtOppStr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStrKeyTyped(evt);
            }
        });

        cbbOppGuild.setMaximumRowCount(20);
        cbbOppGuild.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbOppGuildItemStateChanged(evt);
            }
        });

        cbbOppRace.setMaximumRowCount(20);
        cbbOppRace.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbOppRaceItemStateChanged(evt);
            }
        });

        cbbOppClass.setMaximumRowCount(20);
        cbbOppClass.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbOppClassItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout pOppLayout = new javax.swing.GroupLayout(pOpp);
        pOpp.setLayout(pOppLayout);
        pOppLayout.setHorizontalGroup(
            pOppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pOppLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblOpp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                .addComponent(lblOppSTR)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtOppStr, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cbbOppGuild, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbbOppRace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbbOppClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pOppLayout.setVerticalGroup(
            pOppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pOppLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pOppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblOpp)
                    .addComponent(lblOppSTR)
                    .addComponent(txtOppStr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbOppGuild, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbOppRace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbOppClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );

        pChoose.add(pOpp, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 440, 50));

        pAssume.setBackground(new java.awt.Color(119, 157, 202));
        pAssume.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(40, 80, 126)));
        pAssume.setPreferredSize(new java.awt.Dimension(511, 100));
        pAssume.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pAssumeMouseClicked(evt);
            }
        });

        lblAssuming.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblAssuming.setText("Assuming...");

        lblYouAre.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblYouAre.setText("You are");

        rbtAtk.setBackground(new java.awt.Color(119, 157, 202));
        AtkDef.add(rbtAtk);
        rbtAtk.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        rbtAtk.setSelected(true);
        rbtAtk.setText("Attacker");
        rbtAtk.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbtAtkItemStateChanged(evt);
            }
        });

        rbtDfd.setBackground(new java.awt.Color(119, 157, 202));
        AtkDef.add(rbtDfd);
        rbtDfd.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        rbtDfd.setText("Defender");

        lblCM2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCM2.setForeground(new java.awt.Color(97, 138, 184));
        lblCM2.setText("CLICK ME");

        javax.swing.GroupLayout pAssumeLayout = new javax.swing.GroupLayout(pAssume);
        pAssume.setLayout(pAssumeLayout);
        pAssumeLayout.setHorizontalGroup(
            pAssumeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pAssumeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCM2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                .addComponent(lblAssuming)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblYouAre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbtAtk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbtDfd)
                .addContainerGap())
        );
        pAssumeLayout.setVerticalGroup(
            pAssumeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pAssumeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pAssumeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pAssumeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rbtAtk)
                        .addComponent(lblYouAre)
                        .addComponent(rbtDfd)
                        .addComponent(lblAssuming))
                    .addComponent(lblCM2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );

        pChoose.add(pAssume, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 0, 440, 50));

        pSecond1.setBackground(new java.awt.Color(119, 157, 202));
        pSecond1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(40, 80, 126)));
        pSecond1.setPreferredSize(new java.awt.Dimension(511, 100));

        lblAbyBOT.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblAbyBOT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(95, 133, 179)));

        lblB.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblB.setText("1st Ability ");
        lblB.setPreferredSize(new java.awt.Dimension(80, 14));

        javax.swing.GroupLayout pSecond1Layout = new javax.swing.GroupLayout(pSecond1);
        pSecond1.setLayout(pSecond1Layout);
        pSecond1Layout.setHorizontalGroup(
            pSecond1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pSecond1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pSecond1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pSecond1Layout.createSequentialGroup()
                        .addComponent(lblDBOT1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDBOT2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDBOT3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDBOT4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDBOT5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(148, Short.MAX_VALUE))
            .addGroup(pSecond1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pSecond1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblAbyBOT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        pSecond1Layout.setVerticalGroup(
            pSecond1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pSecond1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pSecond1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblDBOT1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDBOT2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDBOT3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDBOT4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDBOT5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
            .addGroup(pSecond1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pSecond1Layout.createSequentialGroup()
                    .addGap(62, 62, 62)
                    .addComponent(lblAbyBOT, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pChoose.add(pSecond1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 300, 90));

        pSecond2.setBackground(new java.awt.Color(119, 157, 202));
        pSecond2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(40, 80, 126)));
        pSecond2.setPreferredSize(new java.awt.Dimension(511, 100));

        lblAbyMID.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblAbyMID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(95, 133, 179)));

        lblM.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblM.setText("2nd Ability ");
        lblM.setPreferredSize(new java.awt.Dimension(80, 14));

        javax.swing.GroupLayout pSecond2Layout = new javax.swing.GroupLayout(pSecond2);
        pSecond2.setLayout(pSecond2Layout);
        pSecond2Layout.setHorizontalGroup(
            pSecond2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pSecond2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pSecond2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pSecond2Layout.createSequentialGroup()
                        .addComponent(lblDMID1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDMID2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDMID3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDMID4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDMID5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(148, Short.MAX_VALUE))
            .addGroup(pSecond2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pSecond2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblAbyMID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        pSecond2Layout.setVerticalGroup(
            pSecond2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pSecond2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pSecond2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblDMID4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDMID3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDMID2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDMID1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDMID5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
            .addGroup(pSecond2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pSecond2Layout.createSequentialGroup()
                    .addGap(62, 62, 62)
                    .addComponent(lblAbyMID, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pChoose.add(pSecond2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 300, 90));

        pSecond3.setBackground(new java.awt.Color(119, 157, 202));
        pSecond3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(40, 80, 126)));
        pSecond3.setPreferredSize(new java.awt.Dimension(511, 100));

        lblAbyTOP.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblAbyTOP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(95, 133, 179)));
        lblAbyTOP.setPreferredSize(new java.awt.Dimension(2, 20));

        lblT.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblT.setText("3rd Ability ");
        lblT.setPreferredSize(new java.awt.Dimension(80, 14));

        javax.swing.GroupLayout pSecond3Layout = new javax.swing.GroupLayout(pSecond3);
        pSecond3.setLayout(pSecond3Layout);
        pSecond3Layout.setHorizontalGroup(
            pSecond3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pSecond3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pSecond3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pSecond3Layout.createSequentialGroup()
                        .addComponent(lblDTOP1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDTOP2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDTOP3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDTOP4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDTOP5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(148, Short.MAX_VALUE))
            .addGroup(pSecond3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pSecond3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblAbyTOP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        pSecond3Layout.setVerticalGroup(
            pSecond3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pSecond3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pSecond3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblDTOP1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDTOP2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDTOP3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDTOP4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDTOP5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
            .addGroup(pSecond3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pSecond3Layout.createSequentialGroup()
                    .addGap(62, 62, 62)
                    .addComponent(lblAbyTOP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pChoose.add(pSecond3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 300, 90));

        pSecond4.setBackground(new java.awt.Color(119, 157, 202));
        pSecond4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(40, 80, 126)));
        pSecond4.setPreferredSize(new java.awt.Dimension(511, 100));

        counter1.setModel(new javax.swing.SpinnerNumberModel(1, 1, 5, 1));
        counter1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                counter1StateChanged(evt);
            }
        });

        lblCounter1.setText("Current turn?");

        lblWL.setText("You are...");

        rbtWinning.setBackground(new java.awt.Color(119, 157, 202));
        WinLoss.add(rbtWinning);
        rbtWinning.setSelected(true);
        rbtWinning.setText("winning");

        rbtLosing.setBackground(new java.awt.Color(119, 157, 202));
        WinLoss.add(rbtLosing);
        rbtLosing.setText("losing");

        javax.swing.GroupLayout pSecond4Layout = new javax.swing.GroupLayout(pSecond4);
        pSecond4.setLayout(pSecond4Layout);
        pSecond4Layout.setHorizontalGroup(
            pSecond4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pSecond4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pSecond4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pSecond4Layout.createSequentialGroup()
                        .addComponent(lblCounter1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(counter1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pSecond4Layout.createSequentialGroup()
                        .addComponent(lblWL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbtWinning)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbtLosing)))
                .addContainerGap(116, Short.MAX_VALUE))
        );
        pSecond4Layout.setVerticalGroup(
            pSecond4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pSecond4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pSecond4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCounter1)
                    .addComponent(counter1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pSecond4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblWL)
                    .addComponent(rbtWinning)
                    .addComponent(rbtLosing))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pChoose.add(pSecond4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 0, 300, 70));

        pMain.add(pChoose, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 1380, 530));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        java.awt.EventQueue.invokeLater(() -> {
            new MainScreen("", road).setVisible(true);
        });
        this.dispose();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadActionPerformed
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new LoadScreen("").setVisible(true);
            } catch (FileNotFoundException | URISyntaxException ex) {
                Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.dispose();
    }//GEN-LAST:event_btnLoadActionPerformed

    private void btnOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenActionPerformed
        String filename = File.separator + "tmp";
        JFileChooser fc;
        if (!"".equals(road)) {
            fc = new JFileChooser(road);
        } else {
            fc = new JFileChooser(new File(filename));
        }
        fc.addChoosableFileFilter(new FileNameExtensionFilter("Normal text file (*.txt)", "txt"));
        fc.setAcceptAllFileFilterUsed(false);
        fc.setMultiSelectionEnabled(false);
        fc.showOpenDialog(this);
        File selFile = fc.getSelectedFile();

        opening = "";

        if (selFile != null) {
            road = selFile.getAbsolutePath();

            for (int k = road.length() - 1; k > 0; k--) {
                if (road.charAt(k) == '\\') {
                    road = road.substring(0, k);
                    k = 0;
                }
            }

            try {
                opening = lerConteudoArquivo(selFile);
            } catch (IOException ex) {
                Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
            }

            int hifenizado = 0;
            for (int k = 0; k < opening.length(); k++) {
                if ("~".equals(String.valueOf(opening.charAt(k)))) {
                    hifenizado += 1;
                }
            }

            if ((hifenizado == 122 || hifenizado == 125) && !opening.contains("~~")) {
                java.awt.EventQueue.invokeLater(() -> {
                    new MainScreen(opening, road).setVisible(true);
                });
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Only can open sets made by this version of the Ciramolator!");
            }
        }
    }//GEN-LAST:event_btnOpenActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed

        if (pSkill.isVisible()) {
            savingSkill();
        }

        String[] savesarray = new String[125];

        savesarray[0] = "2020_07_05";

        //------------------------------------------------YOUOPP
        savesarray[1] = txtStr.getText();
        savesarray[2] = txtOppStr.getText();
        savesarray[3] = cbbRace.getSelectedIndex() + "";
        savesarray[4] = cbbOppRace.getSelectedIndex() + "";
        savesarray[5] = cbbClass.getSelectedIndex() + "";
        savesarray[6] = cbbOppClass.getSelectedIndex() + "";
        savesarray[7] = cbbGuild.getSelectedIndex() + "";
        savesarray[8] = cbbOppGuild.getSelectedIndex() + "";

        //------------------------------------------------1ST ABY
        savesarray[9] = "1stA";
        //------------------------------------------------DICES
        savesarray[10] = BD1.getSelectedIndex() + "";
        savesarray[11] = BD2.getSelectedIndex() + "";
        savesarray[12] = BD3.getSelectedIndex() + "";
        savesarray[13] = BD4.getSelectedIndex() + "";
        //------------------------------------------------1st Bot Skill
        savesarray[14] = "1stBS";
        String firstone = "";

        for (String item : skills[0]) {
            firstone = firstone.concat(item);
            firstone += "#";
        }
        savesarray[15] = firstone;
        //------------------------------------------------2nd Bot Skill
        savesarray[16] = "2ndBS";
        String secondone = "";

        for (String item : skills[1]) {
            secondone = secondone.concat(item);
            secondone += "#";
        }
        savesarray[17] = secondone;
        //------------------------------------------------3rd Bot Skill
        savesarray[18] = "3rdBS";
        String thirdone = "";

        for (String item : skills[2]) {
            thirdone = thirdone.concat(item);
            thirdone += "#";
        }
        savesarray[19] = thirdone;

        //------------------------------------------------2nd ABY
        savesarray[20] = "2ndA";
        //------------------------------------------------DICES
        savesarray[21] = MD1.getSelectedIndex() + "";
        savesarray[22] = MD2.getSelectedIndex() + "";
        savesarray[23] = MD3.getSelectedIndex() + "";
        savesarray[24] = MD4.getSelectedIndex() + "";
        //------------------------------------------------1st Mid Skill
        savesarray[25] = "1stMS";
        String firsttwo = "";

        for (String item : skills[3]) {
            firsttwo = firsttwo.concat(item);
            firsttwo += "#";
        }
        savesarray[26] = firsttwo;
        //------------------------------------------------2nd Mid Skill
        savesarray[27] = "2ndMS";
        String secondtwo = "";

        for (String item : skills[4]) {
            secondtwo = secondtwo.concat(item);
            secondtwo += "#";
        }
        savesarray[28] = secondtwo;
        //------------------------------------------------3rd Mid Skill
        savesarray[29] = "3rdMS";
        String thirdtwo = "";

        for (String item : skills[5]) {
            thirdtwo = thirdtwo.concat(item);
            thirdtwo += "#";
        }
        savesarray[30] = thirdtwo;

        //------------------------------------------------3rd ABY
        savesarray[31] = "3rdA";
        //------------------------------------------------DICES
        savesarray[32] = TD1.getSelectedIndex() + "";
        savesarray[33] = TD2.getSelectedIndex() + "";
        savesarray[34] = TD3.getSelectedIndex() + "";
        savesarray[35] = TD4.getSelectedIndex() + "";
        //------------------------------------------------1st Mid Skill
        savesarray[36] = "1stTS";
        String firstthree = "";

        for (String item : skills[6]) {
            firstthree = firstthree.concat(item);
            firstthree += "#";
        }
        savesarray[37] = firstthree;
        //------------------------------------------------2nd Mid Skill
        savesarray[38] = "2ndTS";
        String secondthree = "";

        for (String item : skills[7]) {
            secondthree = secondthree.concat(item);
            secondthree += "#";
        }
        savesarray[39] = secondthree;
        //------------------------------------------------3rd Mid Skill
        savesarray[40] = "3rdTS";
        String thirdthree = "";

        for (String item : skills[8]) {
            thirdthree = thirdthree.concat(item);
            thirdthree += "#";
        }
        savesarray[41] = thirdthree;

        //------------------------------------------------ASSUME
        if (rbtAtk.isSelected()) {
            savesarray[42] = "1";
        } else {
            savesarray[42] = "0";
        }
        savesarray[43] = (int) countBlizz1.getValue() + "";
        savesarray[44] = (int) countBlizz2.getValue() + "";
        savesarray[45] = (int) countBlizz3.getValue() + "";
        savesarray[46] = (int) countActiv1.getValue() + "";
        savesarray[47] = (int) countActiv2.getValue() + "";
        savesarray[48] = (int) countActiv3.getValue() + "";
        savesarray[49] = (int) countHit1.getValue() + "";
        savesarray[50] = (int) countHit2.getValue() + "";
        savesarray[51] = (int) countHit3.getValue() + "";
        savesarray[52] = (int) countHitS.getValue() + "";

        //------------------------------------------------BUFFS
        if (NoSeal.isSelected()) {
            savesarray[53] = "0";
        } else if (DMGSeal.isSelected()) {
            savesarray[53] = "1";
        } else if (DodgeSeal.isSelected()) {
            savesarray[53] = "2";
        } else if (RageSeal.isSelected()) {
            savesarray[53] = "3";
        } else if (ResilienceSeal.isSelected()) {
            savesarray[53] = "4";
        } else if (ShieldSeal.isSelected()) {
            savesarray[53] = "5";
        } else if (StenchSeal.isSelected()) {
            savesarray[53] = "6";
        } else if (StrengthSeal.isSelected()) {
            savesarray[53] = "7";
        } else if (ThornSeal.isSelected()) {
            savesarray[53] = "8";
        } else if (BlessingSeal.isSelected()) {
            savesarray[53] = "9";
        }

        if (checkBerserkAura.isSelected()) {
            savesarray[54] = "1";
        } else {
            savesarray[54] = "0";
        }
        if (checkBonusDMGAura.isSelected()) {
            savesarray[55] = "1";
        } else {
            savesarray[55] = "0";
        }
        if (checkDodgeAura.isSelected()) {
            savesarray[56] = "1";
        } else {
            savesarray[56] = "0";
        }
        if (checkFrostbiteAura.isSelected()) {
            savesarray[57] = "1";
        } else {
            savesarray[57] = "0";
        }
        if (checkHealAura.isSelected()) {
            savesarray[58] = "1";
        } else {
            savesarray[58] = "0";
        }
        if (checkIceAura.isSelected()) {
            savesarray[59] = "1";
        } else {
            savesarray[59] = "0";
        }
        if (checkMalusAura.isSelected()) {
            savesarray[60] = "1";
        } else {
            savesarray[60] = "0";
        }
        if (checkRageAura.isSelected()) {
            savesarray[61] = "1";
        } else {
            savesarray[61] = "0";
        }
        if (checkResilienceAura.isSelected()) {
            savesarray[62] = "1";
        } else {
            savesarray[62] = "0";
        }
        if (checkShieldAura.isSelected()) {
            savesarray[63] = "1";
        } else {
            savesarray[63] = "0";
        }
        if (checkStrAura.isSelected()) {
            savesarray[64] = "1";
        } else {
            savesarray[64] = "0";
        }
        if (checkThornAura.isSelected()) {
            savesarray[65] = "1";
        } else {
            savesarray[65] = "0";
        }
        if (checkThunderstruckAura.isSelected()) {
            savesarray[66] = "1";
        } else {
            savesarray[66] = "0";
        }

        savesarray[67] = txtYBerserk.getText();
        savesarray[68] = txtYBlessing.getText();
        savesarray[69] = txtYBlizzard.getText();
        savesarray[70] = txtYBulwark.getText();
        savesarray[71] = txtYCritical.getText();
        savesarray[72] = txtYDMGBonus.getText();
        savesarray[73] = txtYDMGMalus.getText();
        savesarray[74] = txtYDodge.getText();
        savesarray[75] = txtYEclipse.getText();
        savesarray[76] = txtYFrostbite.getText();
        savesarray[77] = txtYIce.getText();
        savesarray[78] = txtYPowder.getText();
        savesarray[79] = txtYRage.getText();
        savesarray[80] = txtYResilience.getText();
        savesarray[81] = txtYRiposte.getText();
        savesarray[82] = txtYRune.getText();
        savesarray[83] = txtYSTRBonus.getText();
        savesarray[84] = txtYSTRMalus.getText();
        savesarray[85] = txtYScarab.getText();
        savesarray[86] = txtYShield.getText();
        savesarray[87] = txtYStench.getText();
        savesarray[88] = txtYTerror.getText();
        savesarray[89] = txtYThorn.getText();
        savesarray[90] = txtYThunderstruck.getText();

        savesarray[91] = txtOBerserk.getText();
        savesarray[92] = txtOBlessing.getText();
        savesarray[93] = txtOBlizzard.getText();
        savesarray[94] = txtOBulwark.getText();
        savesarray[95] = txtOCritical.getText();
        savesarray[96] = txtODMGBonus.getText();
        savesarray[97] = txtODMGMalus.getText();
        savesarray[98] = txtODodge.getText();
        savesarray[99] = txtOEclipse.getText();
        savesarray[100] = txtOFrostbite.getText();
        savesarray[101] = txtOIce.getText();
        savesarray[102] = txtOPowder.getText();
        savesarray[103] = txtORage.getText();
        savesarray[104] = txtOResilience.getText();
        savesarray[105] = txtORiposte.getText();
        savesarray[106] = txtORune.getText();
        savesarray[107] = txtOSTRBonus.getText();
        savesarray[108] = txtOSTRMalus.getText();
        savesarray[109] = txtOScarab.getText();
        savesarray[110] = txtOShield.getText();
        savesarray[111] = txtOStench.getText();
        savesarray[112] = txtOTerror.getText();
        savesarray[113] = txtOThorn.getText();
        savesarray[114] = txtOThunderstruck.getText();

        //------------------------------------------------OTHERS
        if (checkSymb.isSelected()) {
            savesarray[115] = "1";
        } else {
            savesarray[115] = "0";
        }
        if (checkExtra.isSelected()) {
            savesarray[116] = "1";
        } else {
            savesarray[116] = "0";
        }
        if (checkShield.isSelected()) {
            savesarray[117] = "1";
        } else {
            savesarray[117] = "0";
        }
        if (checkHeal.isSelected()) {
            savesarray[118] = "1";
        } else {
            savesarray[118] = "0";
        }
        if (checkBlizz.isSelected()) {
            savesarray[119] = "1";
        } else {
            savesarray[119] = "0";
        }
        if (checkExperimental.isSelected()) {
            savesarray[120] = "1";
        } else {
            savesarray[120] = "0";
        }

        savesarray[121] = eraoque[0] + "#" + eraoque[1] + "#" + eraoque[2] + "#" + eraoque[3] + "#" + eraoque[4] + "#" + eraoque[5] + "#" + eraoque[6] + "#" + eraoque[7] + "#" + eraoque[8] + "#";

        savesarray[122] = counter1.getValue().toString();

        if (rbtWinning.isSelected()) {
            savesarray[123] = "1";
        } else {
            savesarray[123] = "0";
        }

        if (checkSupport.isSelected()) {
            savesarray[124] = "1";
        } else {
            savesarray[124] = "0";
        }

        String savestring = "";
        for (int j = 0; j < 125; j++) {
            savestring += savesarray[j] + "~";
        }
        String filename = File.separator + "tmp";

        JFileChooser fc;
        if (!"".equals(road)) {
            fc = new JFileChooser(road);
        } else {
            fc = new JFileChooser(new File(filename));
        }
        fc.addChoosableFileFilter(new FileNameExtensionFilter("Normal text file (*.txt)", "txt"));
        fc.setAcceptAllFileFilterUsed(false);
        fc.setMultiSelectionEnabled(false);
        fc.showSaveDialog(this);
        if (fc.getSelectedFile() != null) {
            String filenome = fc.getSelectedFile().getName();

            File selFile;
            if (!filenome.contains(".txt")) {
                selFile = new File(fc.getSelectedFile() + ".txt");
                road = fc.getSelectedFile().getAbsolutePath();
                for (int k = road.length() - 1; k > 0; k--) {
                    if (road.charAt(k) == '\\') {
                        road = road.substring(0, k);
                        k = 0;
                    }
                }
            } else {
                selFile = fc.getSelectedFile();
                road = fc.getSelectedFile().getAbsolutePath();
                for (int k = road.length() - 1; k > 0; k--) {
                    if (road.charAt(k) == '\\') {
                        road = road.substring(0, k);
                        k = 0;
                    }
                }
            }

            try {
                try ( FileWriter grava = new FileWriter(selFile, false);  PrintWriter escreve = new PrintWriter(grava)) {

                    escreve.println(savestring);
                }
            } catch (IOException ex) {
                Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnBuffsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuffsActionPerformed
        pBuffing.setVisible(true);
        pAssuming.setVisible(false);
        pSkill.setVisible(false);
        pAuraSeal.setVisible(false);
        allPtoClear();
    }//GEN-LAST:event_btnBuffsActionPerformed

    private void btnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAllActionPerformed
        alldetails = "";
        int temdadoounao;
        if (checkExtra.isSelected()) {
            if (checkTrans.isSelected()) {
                temdadoounao = 36;
            } else {
                temdadoounao = 120;
            }
        } else {
            if (checkTrans.isSelected()) {
                temdadoounao = 28;
            } else {
                temdadoounao = 84;
            }
        }
        for (int rollx = 0; rollx < temdadoounao; rollx++) {

            resetingValues(rollx);

            minimando();

            orderingDices();

            saveAbilityCosts();

            sealing();

            giveSupport();

            buffei();

            int howManyTimes[] = {actbot, actmid, acttop};

            for (int whatabi = 0; whatabi < 3; whatabi++) {
                if (rbtDfd.isSelected()) {
                    defender(whatabi);
                }
                switch (whatabi) {
                    case 0:
                        howManyTimesBot();
                        howManyTimes[0] = actbot;
                        break;
                    case 1:
                        howManyTimesMid();
                        howManyTimes[1] = actmid;
                        break;
                    default:
                        howManyTimesTop();
                        howManyTimes[2] = acttop;
                        break;
                }

                if (howManyTimes[whatabi] != 0) {
                    for (int acti = 0; acti < howManyTimes[whatabi]; acti++) {
                        usandoDados(whatabi + 1);
                        useAbility();
                        abiliTimes();

                        for (int subacti = 0; subacti < 3; subacti++) {
                            int whatskill = (whatabi * 3) + subacti;
                            if ("yes".equals(skills[whatskill][0])) {
                                for (int hmst = 0; hmst < times[whatskill]; hmst++) {

                                    if (("only VS".equals(skills[whatskill][14]) && (skills[whatskill][15].equals(opprace) || skills[whatskill][15].equals(oppguild) || skills[whatskill][15].equals(oppclas))) || (!"only VS".equals(skills[whatskill][14]) && !"only if atk".equals(skills[whatskill][14]) && !"only if dfd".equals(skills[whatskill][14])) || ("only if atk".equals(skills[whatskill][14]) && rbtAtk.isSelected()) || ("only if dfd".equals(skills[whatskill][14]) && rbtDfd.isSelected())) {
                                        if (!"".equals(skills[whatskill][22]) & !"0".equals(skills[whatskill][22]) & alreadyConsumed[whatskill] == 0) {
                                            if (shield - Integer.parseInt(skills[whatskill][22]) > 0) {
                                                shieldconsumed += shield - Integer.parseInt(skills[whatskill][22]);
                                                shield = Integer.parseInt(skills[whatskill][22]);
                                                if (whatskill == 0 || whatskill == 1 || whatskill == 2) {
                                                    alreadyConsumed[0] = 1;
                                                    alreadyConsumed[1] = 1;
                                                    alreadyConsumed[2] = 1;
                                                }
                                                if (whatskill == 3 || whatskill == 4 || whatskill == 5) {
                                                    alreadyConsumed[3] = 1;
                                                    alreadyConsumed[4] = 1;
                                                    alreadyConsumed[5] = 1;
                                                }
                                                if (whatskill == 6 || whatskill == 7 || whatskill == 8) {
                                                    alreadyConsumed[6] = 1;
                                                    alreadyConsumed[7] = 1;
                                                    alreadyConsumed[8] = 1;
                                                }
                                            }
                                        }
                                        updateValues();
                                        int storeValue = typibility(whatskill, Integer.parseInt(skills[whatskill][4]), vallist[whatskill], Integer.parseInt(skills[whatskill][21]));
                                        if ("t".equals(skills[whatskill][1])) {
                                            if ("0".equals(skills[whatskill][3]) || ("t".equals(skills[whatskill][2]) == true && (every[Integer.parseInt(skills[whatskill][3])].equals(oppguild) || every[Integer.parseInt(skills[whatskill][3])].equals(opprace) || every[Integer.parseInt(skills[whatskill][3])].equals(oppclas))) || "t".equals(skills[whatskill][2]) == false && (every[Integer.parseInt(skills[whatskill][3])].equals(guild) || every[Integer.parseInt(skills[whatskill][3])].equals(race) || every[Integer.parseInt(skills[whatskill][3])].equals(clas))) {

                                                discoverAbility(whatskill, storeValue);
                                                if ("Bulwark".equals(skills[whatskill][7])) {
                                                    all[whatskill] = typibility(whatskill, Integer.parseInt(skills[whatskill][4]), fixeall[whatskill], Integer.parseInt(skills[whatskill][21]));
                                                } else {
                                                    all[whatskill] += typibility(whatskill, Integer.parseInt(skills[whatskill][4]), fixeall[whatskill], Integer.parseInt(skills[whatskill][21]));
                                                    if ("if".equals(skills[whatskill][12])) {
                                                        moreif[whatskill] += fixemoreif[whatskill];
                                                    }
                                                }
                                            } else {
                                                if ("Bulwark".equals(skills[whatskill][7])) {
                                                    all[whatskill] = typibility(whatskill, Integer.parseInt(skills[whatskill][4]), fixeall[whatskill], Integer.parseInt(skills[whatskill][21]));
                                                } else {
                                                    all[whatskill] += typibility(whatskill, Integer.parseInt(skills[whatskill][4]), fixeall[whatskill], Integer.parseInt(skills[whatskill][21]));
                                                    if ("if".equals(skills[whatskill][12])) {
                                                        moreif[whatskill] += fixemoreif[whatskill];
                                                    }
                                                }
                                            }
                                        } else {
                                            discoverAbility(whatskill, storeValue);
                                        }
                                    }
                                }
                            }
                        }
                        if (zeroshield != 0) {
                            shield = 0;
                            zeroshield = 0;
                        }

                        switch (whatabi) {
                            case 0:
                                howManyTimesBot();
                                howManyTimes[0] = actbot;
                                break;
                            case 1:
                                howManyTimesMid();
                                howManyTimes[1] = actmid;
                                break;
                            default:
                                howManyTimesTop();
                                howManyTimes[2] = acttop;
                                break;
                        }

                        if (whatabi == 0 & !dicesbot.isEmpty()) {
                            acti = -1;
                        } else if (whatabi == 1 & !dicesmid.isEmpty()) {
                            acti = -1;
                        } else if (whatabi == 2 & !dicestop.isEmpty()) {
                            acti = -1;
                        }
                    }
                }
                if (rbtAtk.isSelected()) {
                    attacker(whatabi);
                }
                queroReusar();
            }
            if (rbtDfd.isSelected()) {
                for (int i = 0; i < (Integer) countHitS.getValue(); i++) {
                    if (scarab == 0) {
                        triggerThorn(thorn);
                        strGain(berserk);
                        if (riposte > 0) {
                            triggerRiposte();
                        }
                    } else {
                        scarab -= 1;
                    }
                }
                swordphase();
            }
            allrollsText(rollx);
        }
        assumingText();
        java.awt.EventQueue.invokeLater(() -> {
            new ResultScreen(setmade, alldetails, assuming, orderingDamage, road).setVisible(true);
        });
    }//GEN-LAST:event_btnAllActionPerformed

    private void btnCalcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcActionPerformed

        resetingValues(999);

        minimando();

        orderingDices();

        saveAbilityCosts();

        sealing();

        giveSupport();

        buffei();

        int howManyTimes[] = {actbot, actmid, acttop};

        for (int whatabi = 0; whatabi < 3; whatabi++) {
            if (rbtDfd.isSelected()) {
                defender(whatabi);
            }
            switch (whatabi) {
                case 0:
                    howManyTimesBot();
                    howManyTimes[0] = actbot;
                    break;
                case 1:
                    howManyTimesMid();
                    howManyTimes[1] = actmid;
                    break;
                default:
                    howManyTimesTop();
                    howManyTimes[2] = acttop;
                    break;
            }

            if (howManyTimes[whatabi] != 0) {
                for (int acti = 0; acti < howManyTimes[whatabi]; acti++) {
                    usandoDados(whatabi + 1);
                    useAbility();
                    abiliTimes();

                    for (int subacti = 0; subacti < 3; subacti++) {
                        int whatskill = (whatabi * 3) + subacti;
                        if ("yes".equals(skills[whatskill][0])) {
                            System.out.println(whatskill + "#" + times[whatskill]);
                            for (int hmst = 0; hmst < times[whatskill]; hmst++) {

//                                    System.out.println(howManyTimes[whatabi]+" - "+whatskill+"#"+hmst+" = "+times[whatskill]);
                                if (("only VS".equals(skills[whatskill][14]) && "0".equals(skills[whatskill][17]) && (skills[whatskill][15].equals(opprace) || skills[whatskill][15].equals(oppguild) || skills[whatskill][15].equals(oppclas))) || ("only VS".equals(skills[whatskill][14]) && "1".equals(skills[whatskill][17]) && ((skills[whatskill][15].equals(opprace) || cbbAnotherOperation.getItemAt(Integer.parseInt(skills[whatskill][19])).equals(opprace)) || (skills[whatskill][15].equals(oppguild) || cbbAnotherOperation.getItemAt(Integer.parseInt(skills[whatskill][19])).equals(oppguild)) || (skills[whatskill][15].equals(oppclas) || cbbAnotherOperation.getItemAt(Integer.parseInt(skills[whatskill][19])).equals(oppclas)))) || (!"only VS".equals(skills[whatskill][14]) && !"only if atk".equals(skills[whatskill][14]) && !"only if dfd".equals(skills[whatskill][14])) || ("only if atk".equals(skills[whatskill][14]) && rbtAtk.isSelected()) || ("only if dfd".equals(skills[whatskill][14]) && rbtDfd.isSelected())) {
                                    if (!"".equals(skills[whatskill][22]) & !"0".equals(skills[whatskill][22]) & alreadyConsumed[whatskill] == 0) {
                                        if (shield - Integer.parseInt(skills[whatskill][22]) > 0) {
                                            shieldconsumed += shield - Integer.parseInt(skills[whatskill][22]);
                                            shield = Integer.parseInt(skills[whatskill][22]);
                                            if (whatskill == 0 || whatskill == 1 || whatskill == 2) {
                                                alreadyConsumed[0] = 1;
                                                alreadyConsumed[1] = 1;
                                                alreadyConsumed[2] = 1;
                                            }
                                            if (whatskill == 3 || whatskill == 4 || whatskill == 5) {
                                                alreadyConsumed[3] = 1;
                                                alreadyConsumed[4] = 1;
                                                alreadyConsumed[5] = 1;
                                            }
                                            if (whatskill == 6 || whatskill == 7 || whatskill == 8) {
                                                alreadyConsumed[6] = 1;
                                                alreadyConsumed[7] = 1;
                                                alreadyConsumed[8] = 1;
                                            }
                                        }
                                    }
                                    updateValues();
                                    int storeValue = typibility(whatskill, Integer.parseInt(skills[whatskill][4]), vallist[whatskill], Integer.parseInt(skills[whatskill][21]));
                                    if ("t".equals(skills[whatskill][1])) {
                                        if ("0".equals(skills[whatskill][3]) || ("t".equals(skills[whatskill][2]) == true && (every[Integer.parseInt(skills[whatskill][3])].equals(oppguild) || every[Integer.parseInt(skills[whatskill][3])].equals(opprace) || every[Integer.parseInt(skills[whatskill][3])].equals(oppclas))) || "t".equals(skills[whatskill][2]) == false && (every[Integer.parseInt(skills[whatskill][3])].equals(guild) || every[Integer.parseInt(skills[whatskill][3])].equals(race) || every[Integer.parseInt(skills[whatskill][3])].equals(clas))) {

                                            discoverAbility(whatskill, storeValue);
                                            if ("Bulwark".equals(skills[whatskill][7])) {
                                                all[whatskill] = typibility(whatskill, Integer.parseInt(skills[whatskill][4]), vallist[whatskill], Integer.parseInt(skills[whatskill][21]));
                                            } else {
                                                all[whatskill] += typibility(whatskill, Integer.parseInt(skills[whatskill][4]), vallist[whatskill], Integer.parseInt(skills[whatskill][21]));
                                                if ("if".equals(skills[whatskill][12])) {
                                                    moreif[whatskill] += fixemoreif[whatskill];
                                                }
                                            }
                                        } else {
                                            if ("Bulwark".equals(skills[whatskill][7])) {
                                                all[whatskill] = typibility(whatskill, Integer.parseInt(skills[whatskill][4]), vallist[whatskill], Integer.parseInt(skills[whatskill][21]));
                                            } else {
                                                all[whatskill] += typibility(whatskill, Integer.parseInt(skills[whatskill][4]), vallist[whatskill], Integer.parseInt(skills[whatskill][21]));
                                                if ("if".equals(skills[whatskill][12])) {
                                                    moreif[whatskill] += fixemoreif[whatskill];
                                                }
                                            }
                                        }
                                    } else {
                                        discoverAbility(whatskill, storeValue);
                                    }
                                }
                            }
                        }
                        if (zeroshield != 0) {
                            shield = 0;
                            zeroshield = 0;
                        }
                    }

                    switch (whatabi) {
                        case 0:
                            howManyTimesBot();
                            howManyTimes[0] = actbot;
                            break;
                        case 1:
                            howManyTimesMid();
                            howManyTimes[1] = actmid;
                            break;
                        default:
                            howManyTimesTop();
                            howManyTimes[2] = acttop;
                            break;
                    }

                    if (whatabi == 0 & !dicesbot.isEmpty()) {
                        acti = -1;
                    } else if (whatabi == 1 & !dicesmid.isEmpty()) {
                        acti = -1;
                    } else if (whatabi == 2 & !dicestop.isEmpty()) {
                        acti = -1;
                    }
                }
            }
            if (rbtAtk.isSelected()) {
                attacker(whatabi);
            }
            queroReusar();
        }
        if (rbtDfd.isSelected()) {
            for (int i = 0; i < (Integer) countHitS.getValue(); i++) {
                if (scarab == 0) {
                    triggerThorn(thorn);
                    strGain(berserk);
                    if (riposte > 0) {
                        triggerRiposte();
                    }
                } else {
                    scarab -= 1;
                }
            }
            swordphase();
        }

        rollsText();
    }//GEN-LAST:event_btnCalcActionPerformed

    private void btnTPlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTPlusActionPerformed
        if ("not".equals(skills[7][0])) {
            skills[7][0] = "yes";
            pT2.setVisible(true);
            showSkill();
            pickingSkill(7);
            allPtoClear();
            pT2.setBackground(new Color(51, 87, 128));
            btnTMinus.setEnabled(true);
        } else {
            skills[8][0] = "yes";
            pT3.setVisible(true);
            showSkill();
            pickingSkill(8);
            allPtoClear();
            pT3.setBackground(new Color(51, 87, 128));
            btnTPlus.setEnabled(false);
        }
        savingSkill();
    }//GEN-LAST:event_btnTPlusActionPerformed

    private void btnTMinusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTMinusActionPerformed
        if ("yes".equals(skills[8][0])) {
            skills[8][0] = "not";
            pT3.setVisible(false);
            showSkill();
            pickingSkill(7);
            allPtoClear();
            pT2.setBackground(new Color(51, 87, 128));
            btnTPlus.setEnabled(true);
        } else {
            skills[7][0] = "not";
            pT2.setVisible(false);
            showSkill();
            pickingSkill(6);
            allPtoClear();
            pT1.setBackground(new Color(51, 87, 128));
            btnTMinus.setEnabled(false);
        }
        savingSkill();
    }//GEN-LAST:event_btnTMinusActionPerformed

    private void btnMPlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMPlusActionPerformed
        if ("not".equals(skills[4][0])) {
            skills[4][0] = "yes";
            pM2.setVisible(true);
            showSkill();
            pickingSkill(4);
            allPtoClear();
            pM2.setBackground(new Color(51, 87, 128));
            btnMMinus.setEnabled(true);
        } else {
            skills[5][0] = "yes";
            pM3.setVisible(true);
            showSkill();
            pickingSkill(5);
            allPtoClear();
            pM3.setBackground(new Color(51, 87, 128));
            btnMPlus.setEnabled(false);
        }
        savingSkill();
    }//GEN-LAST:event_btnMPlusActionPerformed

    private void btnMMinusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMMinusActionPerformed
        if ("yes".equals(skills[5][0])) {
            skills[5][0] = "not";
            pM3.setVisible(false);
            showSkill();
            pickingSkill(4);
            allPtoClear();
            pM2.setBackground(new Color(51, 87, 128));
            btnMPlus.setEnabled(true);
        } else {
            skills[4][0] = "not";
            pM2.setVisible(false);
            showSkill();
            pickingSkill(3);
            allPtoClear();
            pM1.setBackground(new Color(51, 87, 128));
            btnMMinus.setEnabled(false);
        }
        savingSkill();
    }//GEN-LAST:event_btnMMinusActionPerformed

    private void btnBPlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBPlusActionPerformed
        if ("not".equals(skills[1][0])) {
            skills[1][0] = "yes";
            pB2.setVisible(true);
            showSkill();
            pickingSkill(1);
            allPtoClear();
            pB2.setBackground(new Color(51, 87, 128));
            btnBMinus.setEnabled(true);
        } else {
            skills[2][0] = "yes";
            pB3.setVisible(true);
            showSkill();
            pickingSkill(2);
            allPtoClear();
            pB3.setBackground(new Color(51, 87, 128));
            btnBPlus.setEnabled(false);
        }
        savingSkill();
    }//GEN-LAST:event_btnBPlusActionPerformed

    private void btnBMinusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBMinusActionPerformed
        if ("yes".equals(skills[2][0])) {
            skills[2][0] = "not";
            pB3.setVisible(false);
            showSkill();
            pickingSkill(1);
            allPtoClear();
            pB2.setBackground(new Color(51, 87, 128));
            btnBPlus.setEnabled(true);
        } else {
            skills[1][0] = "not";
            pB2.setVisible(false);
            showSkill();
            pickingSkill(0);
            allPtoClear();
            pB1.setBackground(new Color(51, 87, 128));
            btnBMinus.setEnabled(false);
        }
        savingSkill();
    }//GEN-LAST:event_btnBMinusActionPerformed

    private void txtStrKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStrKeyTyped
        String caracteres = "0987654321";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
        if (pSkill.isVisible()) {
            savingSkill();
        }
    }//GEN-LAST:event_txtStrKeyTyped

    private void txtNKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNKeyTyped
        String caracteres = "0987654321rRbBsSyY";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNKeyTyped

    private void pT1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pT1MouseClicked
        showSkill();
        allPtoClear();
        pT1.setBackground(new Color(51, 87, 128));
        pickingSkill(6);
        cbbA.requestFocus();
    }//GEN-LAST:event_pT1MouseClicked

    private void pT2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pT2MouseClicked
        showSkill();
        allPtoClear();
        pT2.setBackground(new Color(51, 87, 128));
        pickingSkill(7);
        cbbA.requestFocus();
    }//GEN-LAST:event_pT2MouseClicked

    private void pT3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pT3MouseClicked
        showSkill();
        allPtoClear();
        pT3.setBackground(new Color(51, 87, 128));
        pickingSkill(8);
        cbbA.requestFocus();
    }//GEN-LAST:event_pT3MouseClicked

    private void pM1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pM1MouseClicked
        showSkill();
        allPtoClear();
        pM1.setBackground(new Color(51, 87, 128));
        pickingSkill(3);
        cbbA.requestFocus();
    }//GEN-LAST:event_pM1MouseClicked

    private void pM2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pM2MouseClicked
        showSkill();
        allPtoClear();
        pM2.setBackground(new Color(51, 87, 128));
        pickingSkill(4);
        cbbA.requestFocus();
    }//GEN-LAST:event_pM2MouseClicked

    private void pM3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pM3MouseClicked
        showSkill();
        allPtoClear();
        pM3.setBackground(new Color(51, 87, 128));
        pickingSkill(5);
        cbbA.requestFocus();
    }//GEN-LAST:event_pM3MouseClicked

    private void pB1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pB1MouseClicked
        showSkill();
        allPtoClear();
        pB1.setBackground(new Color(51, 87, 128));
        pickingSkill(0);
        cbbA.requestFocus();
    }//GEN-LAST:event_pB1MouseClicked

    private void pB2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pB2MouseClicked
        showSkill();
        allPtoClear();
        pB2.setBackground(new Color(51, 87, 128));
        pickingSkill(1);
        cbbA.requestFocus();
    }//GEN-LAST:event_pB2MouseClicked

    private void pB3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pB3MouseClicked
        showSkill();
        allPtoClear();
        pB3.setBackground(new Color(51, 87, 128));
        pickingSkill(2);
        cbbA.requestFocus();
    }//GEN-LAST:event_pB3MouseClicked

    private void pTitleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pTitleMouseClicked
        pBuffing.setVisible(false);
        pAssuming.setVisible(false);
        pSkill.setVisible(false);
        pAuraSeal.setVisible(false);
        allPtoClear();
    }//GEN-LAST:event_pTitleMouseClicked

    private void pBuffsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pBuffsMouseClicked
        pBuffing.setVisible(false);
        pAssuming.setVisible(false);
        pSkill.setVisible(false);
        pAuraSeal.setVisible(true);
        allPtoClear();
    }//GEN-LAST:event_pBuffsMouseClicked

    private void pAssumeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pAssumeMouseClicked
        pBuffing.setVisible(false);
        pAssuming.setVisible(true);
        pSkill.setVisible(false);
        pAuraSeal.setVisible(false);
        allPtoClear();
    }//GEN-LAST:event_pAssumeMouseClicked

    private void BD1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BD1KeyTyped
        if ("N".equals(evt.getKeyChar() + "") || "n".equals(evt.getKeyChar() + "")) {
            BD1.setSelectedIndex(0);
        }
        if ("R".equals(evt.getKeyChar() + "") || "r".equals(evt.getKeyChar() + "")) {
            BD1.setSelectedIndex(1);
        }
        if ("B".equals(evt.getKeyChar() + "") || "b".equals(evt.getKeyChar() + "")) {
            BD1.setSelectedIndex(2);
        }
        if ("Y".equals(evt.getKeyChar() + "") || "y".equals(evt.getKeyChar() + "")) {
            BD1.setSelectedIndex(3);
        }
        if ("S".equals(evt.getKeyChar() + "") || "s".equals(evt.getKeyChar() + "")) {
            BD1.setSelectedIndex(4);
        }
    }//GEN-LAST:event_BD1KeyTyped

    private void BD2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BD2KeyTyped
        if ("N".equals(evt.getKeyChar() + "") || "n".equals(evt.getKeyChar() + "")) {
            BD2.setSelectedIndex(0);
        }
        if ("R".equals(evt.getKeyChar() + "") || "r".equals(evt.getKeyChar() + "")) {
            BD2.setSelectedIndex(1);
        }
        if ("B".equals(evt.getKeyChar() + "") || "b".equals(evt.getKeyChar() + "")) {
            BD2.setSelectedIndex(2);
        }
        if ("Y".equals(evt.getKeyChar() + "") || "y".equals(evt.getKeyChar() + "")) {
            BD2.setSelectedIndex(3);
        }
        if ("S".equals(evt.getKeyChar() + "") || "s".equals(evt.getKeyChar() + "")) {
            BD2.setSelectedIndex(4);
        }
    }//GEN-LAST:event_BD2KeyTyped

    private void BD3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BD3KeyTyped
        if ("N".equals(evt.getKeyChar() + "") || "n".equals(evt.getKeyChar() + "")) {
            BD3.setSelectedIndex(0);
        }
        if ("R".equals(evt.getKeyChar() + "") || "r".equals(evt.getKeyChar() + "")) {
            BD3.setSelectedIndex(1);
        }
        if ("B".equals(evt.getKeyChar() + "") || "b".equals(evt.getKeyChar() + "")) {
            BD3.setSelectedIndex(2);
        }
        if ("Y".equals(evt.getKeyChar() + "") || "y".equals(evt.getKeyChar() + "")) {
            BD3.setSelectedIndex(3);
        }
        if ("S".equals(evt.getKeyChar() + "") || "s".equals(evt.getKeyChar() + "")) {
            BD3.setSelectedIndex(4);
        }
    }//GEN-LAST:event_BD3KeyTyped

    private void BD4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BD4KeyTyped
        if ("N".equals(evt.getKeyChar() + "") || "n".equals(evt.getKeyChar() + "")) {
            BD4.setSelectedIndex(0);
        }
        if ("R".equals(evt.getKeyChar() + "") || "r".equals(evt.getKeyChar() + "")) {
            BD4.setSelectedIndex(1);
        }
        if ("B".equals(evt.getKeyChar() + "") || "b".equals(evt.getKeyChar() + "")) {
            BD4.setSelectedIndex(2);
        }
        if ("Y".equals(evt.getKeyChar() + "") || "y".equals(evt.getKeyChar() + "")) {
            BD4.setSelectedIndex(3);
        }
        if ("S".equals(evt.getKeyChar() + "") || "s".equals(evt.getKeyChar() + "")) {
            BD4.setSelectedIndex(4);
        }
    }//GEN-LAST:event_BD4KeyTyped

    private void MD1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MD1KeyTyped
        if ("N".equals(evt.getKeyChar() + "") || "n".equals(evt.getKeyChar() + "")) {
            MD1.setSelectedIndex(0);
        }
        if ("R".equals(evt.getKeyChar() + "") || "r".equals(evt.getKeyChar() + "")) {
            MD1.setSelectedIndex(1);
        }
        if ("B".equals(evt.getKeyChar() + "") || "b".equals(evt.getKeyChar() + "")) {
            MD1.setSelectedIndex(2);
        }
        if ("Y".equals(evt.getKeyChar() + "") || "y".equals(evt.getKeyChar() + "")) {
            MD1.setSelectedIndex(3);
        }
        if ("S".equals(evt.getKeyChar() + "") || "s".equals(evt.getKeyChar() + "")) {
            MD1.setSelectedIndex(4);
        }
    }//GEN-LAST:event_MD1KeyTyped

    private void MD2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MD2KeyTyped
        if ("N".equals(evt.getKeyChar() + "") || "n".equals(evt.getKeyChar() + "")) {
            MD2.setSelectedIndex(0);
        }
        if ("R".equals(evt.getKeyChar() + "") || "r".equals(evt.getKeyChar() + "")) {
            MD2.setSelectedIndex(1);
        }
        if ("B".equals(evt.getKeyChar() + "") || "b".equals(evt.getKeyChar() + "")) {
            MD2.setSelectedIndex(2);
        }
        if ("Y".equals(evt.getKeyChar() + "") || "y".equals(evt.getKeyChar() + "")) {
            MD2.setSelectedIndex(3);
        }
        if ("S".equals(evt.getKeyChar() + "") || "s".equals(evt.getKeyChar() + "")) {
            MD2.setSelectedIndex(4);
        }
    }//GEN-LAST:event_MD2KeyTyped

    private void MD3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MD3KeyTyped
        if ("N".equals(evt.getKeyChar() + "") || "n".equals(evt.getKeyChar() + "")) {
            MD3.setSelectedIndex(0);
        }
        if ("R".equals(evt.getKeyChar() + "") || "r".equals(evt.getKeyChar() + "")) {
            MD3.setSelectedIndex(1);
        }
        if ("B".equals(evt.getKeyChar() + "") || "b".equals(evt.getKeyChar() + "")) {
            MD3.setSelectedIndex(2);
        }
        if ("Y".equals(evt.getKeyChar() + "") || "y".equals(evt.getKeyChar() + "")) {
            MD3.setSelectedIndex(3);
        }
        if ("S".equals(evt.getKeyChar() + "") || "s".equals(evt.getKeyChar() + "")) {
            MD3.setSelectedIndex(4);
        }
    }//GEN-LAST:event_MD3KeyTyped

    private void MD4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MD4KeyTyped
        if ("N".equals(evt.getKeyChar() + "") || "n".equals(evt.getKeyChar() + "")) {
            MD4.setSelectedIndex(0);
        }
        if ("R".equals(evt.getKeyChar() + "") || "r".equals(evt.getKeyChar() + "")) {
            MD4.setSelectedIndex(1);
        }
        if ("B".equals(evt.getKeyChar() + "") || "b".equals(evt.getKeyChar() + "")) {
            MD4.setSelectedIndex(2);
        }
        if ("Y".equals(evt.getKeyChar() + "") || "y".equals(evt.getKeyChar() + "")) {
            MD4.setSelectedIndex(3);
        }
        if ("S".equals(evt.getKeyChar() + "") || "s".equals(evt.getKeyChar() + "")) {
            MD4.setSelectedIndex(4);
        }
    }//GEN-LAST:event_MD4KeyTyped

    private void TD1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TD1KeyTyped
        if ("N".equals(evt.getKeyChar() + "") || "n".equals(evt.getKeyChar() + "")) {
            TD1.setSelectedIndex(0);
        }
        if ("R".equals(evt.getKeyChar() + "") || "r".equals(evt.getKeyChar() + "")) {
            TD1.setSelectedIndex(1);
        }
        if ("B".equals(evt.getKeyChar() + "") || "b".equals(evt.getKeyChar() + "")) {
            TD1.setSelectedIndex(2);
        }
        if ("Y".equals(evt.getKeyChar() + "") || "y".equals(evt.getKeyChar() + "")) {
            TD1.setSelectedIndex(3);
        }
        if ("S".equals(evt.getKeyChar() + "") || "s".equals(evt.getKeyChar() + "")) {
            TD1.setSelectedIndex(4);
        }
    }//GEN-LAST:event_TD1KeyTyped

    private void TD2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TD2KeyTyped
        if ("N".equals(evt.getKeyChar() + "") || "n".equals(evt.getKeyChar() + "")) {
            TD2.setSelectedIndex(0);
        }
        if ("R".equals(evt.getKeyChar() + "") || "r".equals(evt.getKeyChar() + "")) {
            TD2.setSelectedIndex(1);
        }
        if ("B".equals(evt.getKeyChar() + "") || "b".equals(evt.getKeyChar() + "")) {
            TD2.setSelectedIndex(2);
        }
        if ("Y".equals(evt.getKeyChar() + "") || "y".equals(evt.getKeyChar() + "")) {
            TD2.setSelectedIndex(3);
        }
        if ("S".equals(evt.getKeyChar() + "") || "s".equals(evt.getKeyChar() + "")) {
            TD2.setSelectedIndex(4);
        }
    }//GEN-LAST:event_TD2KeyTyped

    private void TD3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TD3KeyTyped
        if ("N".equals(evt.getKeyChar() + "") || "n".equals(evt.getKeyChar() + "")) {
            TD3.setSelectedIndex(0);
        }
        if ("R".equals(evt.getKeyChar() + "") || "r".equals(evt.getKeyChar() + "")) {
            TD3.setSelectedIndex(1);
        }
        if ("B".equals(evt.getKeyChar() + "") || "b".equals(evt.getKeyChar() + "")) {
            TD3.setSelectedIndex(2);
        }
        if ("Y".equals(evt.getKeyChar() + "") || "y".equals(evt.getKeyChar() + "")) {
            TD3.setSelectedIndex(3);
        }
        if ("S".equals(evt.getKeyChar() + "") || "s".equals(evt.getKeyChar() + "")) {
            TD3.setSelectedIndex(4);
        }
    }//GEN-LAST:event_TD3KeyTyped

    private void TD4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TD4KeyTyped
        if ("N".equals(evt.getKeyChar() + "") || "n".equals(evt.getKeyChar() + "")) {
            TD4.setSelectedIndex(0);
        }
        if ("R".equals(evt.getKeyChar() + "") || "r".equals(evt.getKeyChar() + "")) {
            TD4.setSelectedIndex(1);
        }
        if ("B".equals(evt.getKeyChar() + "") || "b".equals(evt.getKeyChar() + "")) {
            TD4.setSelectedIndex(2);
        }
        if ("Y".equals(evt.getKeyChar() + "") || "y".equals(evt.getKeyChar() + "")) {
            TD4.setSelectedIndex(3);
        }
        if ("S".equals(evt.getKeyChar() + "") || "s".equals(evt.getKeyChar() + "")) {
            TD4.setSelectedIndex(4);
        }
    }//GEN-LAST:event_TD4KeyTyped

    private void D1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_D1KeyTyped
        if ("R".equals(evt.getKeyChar() + "") || "r".equals(evt.getKeyChar() + "")) {
            D1.setSelectedIndex(0);
        }
        if ("B".equals(evt.getKeyChar() + "") || "b".equals(evt.getKeyChar() + "")) {
            D1.setSelectedIndex(1);
        }
        if ("Y".equals(evt.getKeyChar() + "") || "y".equals(evt.getKeyChar() + "")) {
            D1.setSelectedIndex(2);
        }
        if ("S".equals(evt.getKeyChar() + "") || "s".equals(evt.getKeyChar() + "")) {
            D1.setSelectedIndex(3);
        }
    }//GEN-LAST:event_D1KeyTyped

    private void D2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_D2KeyTyped
        if ("R".equals(evt.getKeyChar() + "") || "r".equals(evt.getKeyChar() + "")) {
            D2.setSelectedIndex(0);
        }
        if ("B".equals(evt.getKeyChar() + "") || "b".equals(evt.getKeyChar() + "")) {
            D2.setSelectedIndex(1);
        }
        if ("Y".equals(evt.getKeyChar() + "") || "y".equals(evt.getKeyChar() + "")) {
            D2.setSelectedIndex(2);
        }
        if ("S".equals(evt.getKeyChar() + "") || "s".equals(evt.getKeyChar() + "")) {
            D2.setSelectedIndex(3);
        }
    }//GEN-LAST:event_D2KeyTyped

    private void D3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_D3KeyTyped
        if ("R".equals(evt.getKeyChar() + "") || "r".equals(evt.getKeyChar() + "")) {
            D3.setSelectedIndex(0);
        }
        if ("B".equals(evt.getKeyChar() + "") || "b".equals(evt.getKeyChar() + "")) {
            D3.setSelectedIndex(1);
        }
        if ("Y".equals(evt.getKeyChar() + "") || "y".equals(evt.getKeyChar() + "")) {
            D3.setSelectedIndex(2);
        }
        if ("S".equals(evt.getKeyChar() + "") || "s".equals(evt.getKeyChar() + "")) {
            D3.setSelectedIndex(3);
        }
    }//GEN-LAST:event_D3KeyTyped

    private void D4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_D4KeyTyped
        if ("R".equals(evt.getKeyChar() + "") || "r".equals(evt.getKeyChar() + "")) {
            D4.setSelectedIndex(0);
        }
        if ("B".equals(evt.getKeyChar() + "") || "b".equals(evt.getKeyChar() + "")) {
            D4.setSelectedIndex(1);
        }
        if ("Y".equals(evt.getKeyChar() + "") || "y".equals(evt.getKeyChar() + "")) {
            D4.setSelectedIndex(2);
        }
        if ("S".equals(evt.getKeyChar() + "") || "s".equals(evt.getKeyChar() + "")) {
            D4.setSelectedIndex(3);
        }
    }//GEN-LAST:event_D4KeyTyped

    private void D5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_D5KeyTyped
        if ("R".equals(evt.getKeyChar() + "") || "r".equals(evt.getKeyChar() + "")) {
            D5.setSelectedIndex(0);
        }
        if ("B".equals(evt.getKeyChar() + "") || "b".equals(evt.getKeyChar() + "")) {
            D5.setSelectedIndex(1);
        }
        if ("Y".equals(evt.getKeyChar() + "") || "y".equals(evt.getKeyChar() + "")) {
            D5.setSelectedIndex(2);
        }
        if ("S".equals(evt.getKeyChar() + "") || "s".equals(evt.getKeyChar() + "")) {
            D5.setSelectedIndex(3);
        }
    }//GEN-LAST:event_D5KeyTyped

    private void D6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_D6KeyTyped
        if ("R".equals(evt.getKeyChar() + "") || "r".equals(evt.getKeyChar() + "")) {
            D6.setSelectedIndex(0);
        }
        if ("B".equals(evt.getKeyChar() + "") || "b".equals(evt.getKeyChar() + "")) {
            D6.setSelectedIndex(1);
        }
        if ("Y".equals(evt.getKeyChar() + "") || "y".equals(evt.getKeyChar() + "")) {
            D6.setSelectedIndex(2);
        }
        if ("S".equals(evt.getKeyChar() + "") || "s".equals(evt.getKeyChar() + "")) {
            D6.setSelectedIndex(3);
        }
    }//GEN-LAST:event_D6KeyTyped

    private void D7KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_D7KeyTyped
        if ("R".equals(evt.getKeyChar() + "") || "r".equals(evt.getKeyChar() + "")) {
            D7.setSelectedIndex(0);
        }
        if ("B".equals(evt.getKeyChar() + "") || "b".equals(evt.getKeyChar() + "")) {
            D7.setSelectedIndex(1);
        }
        if ("Y".equals(evt.getKeyChar() + "") || "y".equals(evt.getKeyChar() + "")) {
            D7.setSelectedIndex(2);
        }
        if ("S".equals(evt.getKeyChar() + "") || "s".equals(evt.getKeyChar() + "")) {
            D7.setSelectedIndex(3);
        }
    }//GEN-LAST:event_D7KeyTyped

    private void checkExtraItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkExtraItemStateChanged
        if (checkExtra.isSelected()) {
            D7.setVisible(true);
            if (!checkTrans.isSelected()) {
                orderingDamage = new String[120][2];
            } else {
                orderingDamage = new String[36][2];
            }
            diceLimit = 7;
        } else {
            D7.setVisible(false);
            if (!checkTrans.isSelected()) {
                orderingDamage = new String[84][2];
            } else {
                orderingDamage = new String[28][2];
            }
            diceLimit = 6;
        }
    }//GEN-LAST:event_checkExtraItemStateChanged

    private void checkExperimentalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkExperimentalItemStateChanged
        if (checkExperimental.isSelected()) {
            BlessingSeal.setVisible(true);
            cbbType.setModel(new DefaultComboBoxModel<>(new String[]{"None", "Brave", "Corrupt", "Icy", "Necrotic", "Noble", "Runic", "Sacred", "Timed", "Freaky"}));
            cbbA.setModel(new DefaultComboBoxModel<>(new String[]{"Avenge", "Backstab", "Berserk", "Blessing", "Blizzard", "Bulwark", "Critical", "Death Stare", "Deja Vu", "Dice Change", "Dispel", "+DMG", "-DMG", "Dodge", "Eclipse", "Explode", "Fireball", "Frostbite", "Heal", "Heroism", "Hit", "Ice", "Iceball", "Inspire", "Kagejutsu", "Life Drain", "Lightning", "Mimic", "Physical Attack", "Pierce", "Portal", "Powder", "Purify", "Rage", "Resilience", "Restore", "Riposte", "Rune", "Scarab", "Shadow", "Shield", "Shield Bash", "Shock", "Smite", "Spellbreaker", "Stench", "Storm", "Strength +", "Strength -", "Strength /", "Strength =", "Strength Drain", "Strength x", "Symbiosis", "Terror", "Thorn", "Thunderstruck"}));
            cbbA.setSelectedItem(skills[currentSkill][7]);
        } else {
            lblWL.setVisible(false);
            rbtWinning.setVisible(false);
            rbtLosing.setVisible(false);
            BlessingSeal.setVisible(false);
            NoSeal.setSelected(true);
            cbbType.setModel(new DefaultComboBoxModel<>(new String[]{"None", "Brave", "Corrupt", "Icy", "Necrotic", "Noble", "Runic"}));
            lblCounter1.setVisible(false);
            counter1.setVisible(false);
            rbtWinning.setVisible(false);
            rbtLosing.setVisible(false);
            cbbA.setModel(new DefaultComboBoxModel<>(new String[]{"Backstab", "Berserk", "Blessing", "Blizzard", "Bulwark", "Critical", "Death Stare", "Deja Vu", "Dice Change", "Dispel", "+DMG", "-DMG", "Dodge", "Eclipse", "Fireball", "Frostbite", "Heal", "Hit", "Ice", "Inspire", "Life Drain", "Lightning", "Mimic", "Physical Attack", "Portal", "Powder", "Purify", "Rage", "Resilience", "Riposte", "Rune", "Scarab", "Shield", "Shield Bash", "Shock", "Smite", "Spellbreaker", "Stench", "Storm", "Strength +", "Strength -", "Strength /", "Strength =", "Strength Drain", "Strength x", "Symbiosis", "Terror", "Thorn", "Thunderstruck"}));
            if ("Avenge".equals(skills[currentSkill][7]) || "Explode".equals(skills[currentSkill][7]) || "Explode".equals(skills[currentSkill][7]) || "Kagejutsu".equals(skills[currentSkill][7]) || "Pierce".equals(skills[currentSkill][7]) || "Restore".equals(skills[currentSkill][7]) || "Shadow".equals(skills[currentSkill][7])) {
                cbbA.setSelectedIndex(0);
            }
        }
        if (pSkill.isVisible()) {
            savingSkill();
        }
    }//GEN-LAST:event_checkExperimentalItemStateChanged

    private void cbbDiceChangedKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbbDiceChangedKeyTyped
        if ("R".equals(evt.getKeyChar() + "") || "r".equals(evt.getKeyChar() + "")) {
            cbbDiceChanged.setSelectedIndex(0);
        }
        if ("B".equals(evt.getKeyChar() + "") || "b".equals(evt.getKeyChar() + "")) {
            cbbDiceChanged.setSelectedIndex(1);
        }
        if ("Y".equals(evt.getKeyChar() + "") || "y".equals(evt.getKeyChar() + "")) {
            cbbDiceChanged.setSelectedIndex(2);
        }
        if ("S".equals(evt.getKeyChar() + "") || "s".equals(evt.getKeyChar() + "")) {
            cbbDiceChanged.setSelectedIndex(3);
        }
    }//GEN-LAST:event_cbbDiceChangedKeyTyped

    private void cbbDiceChangeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbbDiceChangeKeyTyped
        if ("R".equals(evt.getKeyChar() + "") || "r".equals(evt.getKeyChar() + "")) {
            cbbDiceChange.setSelectedIndex(0);
        }
        if ("B".equals(evt.getKeyChar() + "") || "b".equals(evt.getKeyChar() + "")) {
            cbbDiceChange.setSelectedIndex(1);
        }
        if ("Y".equals(evt.getKeyChar() + "") || "y".equals(evt.getKeyChar() + "")) {
            cbbDiceChange.setSelectedIndex(2);
        }
        if ("S".equals(evt.getKeyChar() + "") || "s".equals(evt.getKeyChar() + "")) {
            cbbDiceChange.setSelectedIndex(3);
        }
    }//GEN-LAST:event_cbbDiceChangeKeyTyped

    private void cbbTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbTypeItemStateChanged
        switch (cbbType.getSelectedIndex()) {
            case 1:
            case 5:
                checkType.setVisible(true);
                checkType.setText(cbbType.getSelectedItem() + " activated?");
                lblCounter1.setVisible(false);
                counter1.setVisible(false);
                lblbuffdebuff.setVisible(false);
                counterBuffDebuff.setVisible(false);
                break;
            case 2:
            case 4:
                checkType.setVisible(false);
                lblCounter1.setVisible(false);
                counter1.setVisible(false);
                lblbuffdebuff.setVisible(true);
                counterBuffDebuff.setVisible(true);
                break;
            default:
                checkType.setVisible(false);
                lblCounter1.setVisible(false);
                counter1.setVisible(false);
                lblbuffdebuff.setVisible(false);
                counterBuffDebuff.setVisible(false);
                break;
        }
        savingSkill();
    }//GEN-LAST:event_cbbTypeItemStateChanged

    private void cbbAItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbAItemStateChanged
        cbbAmudanca();
        savingSkill();
    }//GEN-LAST:event_cbbAItemStateChanged

    private void checkAllItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkAllItemStateChanged
        if (checkAll.isSelected()) {
            cbbAny.setVisible(true);
            cbbAddDMG.addItem("if");
        } else {
            cbbAny.setVisible(false);
            cbbAddDMG.removeItem("if");
        }
        savingSkill();
    }//GEN-LAST:event_checkAllItemStateChanged

    private void cbbAddDMGItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbAddDMGItemStateChanged
        mudandoOperation(cbbAddDMG.getSelectedItem().toString());
        savingSkill();
    }//GEN-LAST:event_cbbAddDMGItemStateChanged

    private void cbbOperationItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbOperationItemStateChanged
        switch (cbbAddDMG.getSelectedItem().toString()) {
            case "Dice":
                switch (cbbOperation.getSelectedItem().toString()) {
                    case "[VAL] x":
                        lblAnotherWall.setVisible(true);
                        lblAddAnother.setVisible(true);
                        cbbAnotherAddDMG.setVisible(true);
                        txtAddDMG.setVisible(false);
                        if (cbbAnotherAddDMG.getSelectedIndex() != 0) {
                            txtAnotherAddDMG.setVisible(true);
                            if (cbbAnotherAddDMG.getSelectedIndex() == 1) {
                                cbbAnotherOperation.setVisible(true);
                            }
                        } else {
                            txtAnotherAddDMG.setVisible(false);
                            cbbAnotherOperation.setVisible(false);
                        }
                        break;
                    case "+[VAL] per":
                        lblAnotherWall.setVisible(false);
                        lblAddAnother.setVisible(false);
                        cbbAnotherAddDMG.setVisible(false);
                        cbbAnotherAddDMG.setSelectedIndex(0);
                        txtAnotherAddDMG.setVisible(false);
                        cbbAnotherOperation.setVisible(false);
                        txtAddDMG.setVisible(true);
                        break;
                    case "-[VAL] per":
                        lblAnotherWall.setVisible(false);
                        lblAddAnother.setVisible(false);
                        cbbAnotherAddDMG.setVisible(false);
                        cbbAnotherAddDMG.setSelectedIndex(0);
                        txtAnotherAddDMG.setVisible(false);
                        cbbAnotherOperation.setVisible(false);
                        txtAddDMG.setVisible(true);
                        break;
                }
                break;
            case "Opp Dice":
                switch (cbbOperation.getSelectedItem().toString()) {
                    case "[VAL] x":
                        lblAnotherWall.setVisible(true);
                        lblAddAnother.setVisible(true);
                        cbbAnotherAddDMG.setVisible(true);
                        if (cbbAnotherAddDMG.getSelectedIndex() != 0) {
                            txtAnotherAddDMG.setVisible(true);
                            if (cbbAnotherAddDMG.getSelectedIndex() == 1) {
                                cbbAnotherOperation.setVisible(true);
                            }
                        } else {
                            txtAnotherAddDMG.setVisible(false);
                            cbbAnotherOperation.setVisible(false);
                        }
                        txtAddDMG.setVisible(false);
                        break;
                    case "+[VAL] per":
                        lblAnotherWall.setVisible(false);
                        lblAddAnother.setVisible(false);
                        cbbAnotherAddDMG.setVisible(false);
                        cbbAnotherAddDMG.setSelectedIndex(0);
                        txtAnotherAddDMG.setVisible(false);
                        cbbAnotherOperation.setVisible(false);
                        txtAddDMG.setVisible(true);
                        break;
                    case "-[VAL] per":
                        lblAnotherWall.setVisible(false);
                        lblAddAnother.setVisible(false);
                        cbbAnotherAddDMG.setVisible(false);
                        cbbAnotherAddDMG.setSelectedIndex(0);
                        txtAnotherAddDMG.setVisible(false);
                        cbbAnotherOperation.setVisible(false);
                        txtAddDMG.setVisible(true);
                        break;
                }
                break;
            case "VS":
                if ("[VAL] VS".equals(cbbOperation.getSelectedItem().toString())) {
                    txtAddDMG.setVisible(true);
                } else {
                    txtAddDMG.setVisible(false);
                }
                break;
            case "Ally":
                switch (cbbOperation.getSelectedItem().toString()) {
                    case "[VAL] x":
                        lblAnotherWall.setVisible(true);
                        lblAddAnother.setVisible(true);
                        cbbAnotherAddDMG.setVisible(true);
                        if (cbbAnotherAddDMG.getSelectedIndex() != 0) {
                            txtAnotherAddDMG.setVisible(true);
                            if (cbbAnotherAddDMG.getSelectedIndex() == 1) {
                                cbbAnotherOperation.setVisible(true);
                            }
                        } else {
                            txtAnotherAddDMG.setVisible(false);
                            cbbAnotherOperation.setVisible(false);
                        }
                        txtAddDMG.setVisible(false);
                        break;
                    case "+[VAL] per":
                        lblAnotherWall.setVisible(false);
                        lblAddAnother.setVisible(false);
                        cbbAnotherAddDMG.setVisible(false);
                        cbbAnotherAddDMG.setSelectedIndex(0);
                        txtAnotherAddDMG.setVisible(false);
                        cbbAnotherOperation.setVisible(false);
                        txtAddDMG.setVisible(true);
                        break;
                    case "-[VAL] per":
                        lblAnotherWall.setVisible(false);
                        lblAddAnother.setVisible(false);
                        cbbAnotherAddDMG.setVisible(false);
                        cbbAnotherAddDMG.setSelectedIndex(0);
                        txtAnotherAddDMG.setVisible(false);
                        cbbAnotherOperation.setVisible(false);
                        txtAddDMG.setVisible(true);
                        break;
                }
                break;
            case "Opp":
                switch (cbbOperation.getSelectedItem().toString()) {
                    case "[VAL] x":
                        lblAnotherWall.setVisible(true);
                        lblAddAnother.setVisible(true);
                        cbbAnotherAddDMG.setVisible(true);
                        if (cbbAnotherAddDMG.getSelectedIndex() != 0) {
                            txtAnotherAddDMG.setVisible(true);
                            if (cbbAnotherAddDMG.getSelectedIndex() == 1) {
                                cbbAnotherOperation.setVisible(true);
                            }
                        } else {
                            txtAnotherAddDMG.setVisible(false);
                            cbbAnotherOperation.setVisible(false);
                        }
                        txtAddDMG.setVisible(false);
                        break;
                    case "+[VAL] per":
                        lblAnotherWall.setVisible(false);
                        lblAddAnother.setVisible(false);
                        cbbAnotherAddDMG.setVisible(false);
                        cbbAnotherAddDMG.setSelectedIndex(0);
                        txtAnotherAddDMG.setVisible(false);
                        cbbAnotherOperation.setVisible(false);
                        txtAddDMG.setVisible(true);
                        break;
                    case "-[VAL] per":
                        lblAnotherWall.setVisible(false);
                        lblAddAnother.setVisible(false);
                        cbbAnotherAddDMG.setVisible(false);
                        cbbAnotherAddDMG.setSelectedIndex(0);
                        txtAnotherAddDMG.setVisible(false);
                        cbbAnotherOperation.setVisible(false);
                        txtAddDMG.setVisible(true);
                        break;
                }
                break;
            case "Attacker":
                if ("[VAL] if atk".equals(cbbOperation.getSelectedItem().toString())) {
                    txtAddDMG.setVisible(true);
                } else {
                    txtAddDMG.setVisible(false);
                }
                break;
            case "Defender":
                if ("[VAL] if dfd".equals(cbbOperation.getSelectedItem().toString())) {
                    txtAddDMG.setVisible(true);
                } else {
                    txtAddDMG.setVisible(false);
                }
                break;
            case "Bonus/Malus":
                switch (cbbOperation.getSelectedItem().toString()) {
                    case "[VAL] x":
                        lblAnotherWall.setVisible(true);
                        lblAddAnother.setVisible(true);
                        cbbAnotherAddDMG.setVisible(true);
                        if (cbbAnotherAddDMG.getSelectedIndex() != 0) {
                            txtAnotherAddDMG.setVisible(true);
                            if (cbbAnotherAddDMG.getSelectedIndex() == 1) {
                                cbbAnotherOperation.setVisible(true);
                            }
                        } else {
                            txtAnotherAddDMG.setVisible(false);
                            cbbAnotherOperation.setVisible(false);
                        }
                        txtAddDMG.setVisible(false);
                        break;
                    case "[VAL] +":
                        lblAnotherWall.setVisible(true);
                        lblAddAnother.setVisible(true);
                        cbbAnotherAddDMG.setVisible(true);
                        if (cbbAnotherAddDMG.getSelectedIndex() != 0) {
                            txtAnotherAddDMG.setVisible(true);
                            if (cbbAnotherAddDMG.getSelectedIndex() == 1) {
                                cbbAnotherOperation.setVisible(true);
                            }
                        } else {
                            txtAnotherAddDMG.setVisible(false);
                            cbbAnotherOperation.setVisible(false);
                        }
                        txtAddDMG.setVisible(false);
                        break;
                    case "[VAL] -":
                        lblAnotherWall.setVisible(true);
                        lblAddAnother.setVisible(true);
                        cbbAnotherAddDMG.setVisible(true);
                        if (cbbAnotherAddDMG.getSelectedIndex() != 0) {
                            txtAnotherAddDMG.setVisible(true);
                            if (cbbAnotherAddDMG.getSelectedIndex() == 1) {
                                cbbAnotherOperation.setVisible(true);
                            }
                        } else {
                            txtAnotherAddDMG.setVisible(false);
                            cbbAnotherOperation.setVisible(false);
                        }
                        txtAddDMG.setVisible(false);
                        break;
                    case "/ [VAL]":
                        lblAnotherWall.setVisible(true);
                        lblAddAnother.setVisible(true);
                        cbbAnotherAddDMG.setVisible(true);
                        if (cbbAnotherAddDMG.getSelectedIndex() != 0) {
                            txtAnotherAddDMG.setVisible(true);
                            if (cbbAnotherAddDMG.getSelectedIndex() == 1) {
                                cbbAnotherOperation.setVisible(true);
                            }
                        } else {
                            txtAnotherAddDMG.setVisible(false);
                            cbbAnotherOperation.setVisible(false);
                        }
                        txtAddDMG.setVisible(false);
                        break;
                    case "+[VAL] per":
                        lblAnotherWall.setVisible(false);
                        lblAddAnother.setVisible(false);
                        cbbAnotherAddDMG.setVisible(false);
                        cbbAnotherAddDMG.setSelectedIndex(0);
                        txtAnotherAddDMG.setVisible(false);
                        cbbAnotherOperation.setVisible(false);
                        txtAddDMG.setVisible(true);
                        break;
                    case "-[VAL] per":
                        lblAnotherWall.setVisible(false);
                        lblAddAnother.setVisible(false);
                        cbbAnotherAddDMG.setVisible(false);
                        cbbAnotherAddDMG.setSelectedIndex(0);
                        txtAnotherAddDMG.setVisible(false);
                        cbbAnotherOperation.setVisible(false);
                        txtAddDMG.setVisible(true);
                        break;
                }
                break;
            case "opp Bonus/Malus":
                switch (cbbOperation.getSelectedItem().toString()) {
                    case "[VAL] x":
                        lblAnotherWall.setVisible(true);
                        lblAddAnother.setVisible(true);
                        cbbAnotherAddDMG.setVisible(true);
                        if (cbbAnotherAddDMG.getSelectedIndex() != 0) {
                            txtAnotherAddDMG.setVisible(true);
                            if (cbbAnotherAddDMG.getSelectedIndex() == 1) {
                                cbbAnotherOperation.setVisible(true);
                            }
                        } else {
                            txtAnotherAddDMG.setVisible(false);
                            cbbAnotherOperation.setVisible(false);
                        }
                        txtAddDMG.setVisible(false);
                        break;
                    case "[VAL] +":
                        lblAnotherWall.setVisible(true);
                        lblAddAnother.setVisible(true);
                        cbbAnotherAddDMG.setVisible(true);
                        if (cbbAnotherAddDMG.getSelectedIndex() != 0) {
                            txtAnotherAddDMG.setVisible(true);
                            if (cbbAnotherAddDMG.getSelectedIndex() == 1) {
                                cbbAnotherOperation.setVisible(true);
                            }
                        } else {
                            txtAnotherAddDMG.setVisible(false);
                            cbbAnotherOperation.setVisible(false);
                        }
                        txtAddDMG.setVisible(false);
                        break;
                    case "[VAL] -":
                        lblAnotherWall.setVisible(true);
                        lblAddAnother.setVisible(true);
                        cbbAnotherAddDMG.setVisible(true);
                        if (cbbAnotherAddDMG.getSelectedIndex() != 0) {
                            txtAnotherAddDMG.setVisible(true);
                            if (cbbAnotherAddDMG.getSelectedIndex() == 1) {
                                cbbAnotherOperation.setVisible(true);
                            }
                        } else {
                            txtAnotherAddDMG.setVisible(false);
                            cbbAnotherOperation.setVisible(false);
                        }
                        txtAddDMG.setVisible(false);
                        break;
                    case "/ [VAL]":
                        lblAnotherWall.setVisible(true);
                        lblAddAnother.setVisible(true);
                        cbbAnotherAddDMG.setVisible(true);
                        if (cbbAnotherAddDMG.getSelectedIndex() != 0) {
                            txtAnotherAddDMG.setVisible(true);
                            if (cbbAnotherAddDMG.getSelectedIndex() == 1) {
                                cbbAnotherOperation.setVisible(true);
                            }
                        } else {
                            txtAnotherAddDMG.setVisible(false);
                            cbbAnotherOperation.setVisible(false);
                        }
                        txtAddDMG.setVisible(false);
                        break;
                    case "+[VAL] per":
                        lblAnotherWall.setVisible(false);
                        lblAddAnother.setVisible(false);
                        cbbAnotherAddDMG.setVisible(false);
                        cbbAnotherAddDMG.setSelectedIndex(0);
                        txtAnotherAddDMG.setVisible(false);
                        cbbAnotherOperation.setVisible(false);
                        txtAddDMG.setVisible(true);
                        break;
                    case "-[VAL] per":
                        lblAnotherWall.setVisible(false);
                        lblAddAnother.setVisible(false);
                        cbbAnotherAddDMG.setVisible(false);
                        cbbAnotherAddDMG.setSelectedIndex(0);
                        txtAnotherAddDMG.setVisible(false);
                        cbbAnotherOperation.setVisible(false);
                        txtAddDMG.setVisible(true);
                        break;
                }
                break;
        }
        savingSkill();
    }//GEN-LAST:event_cbbOperationItemStateChanged

    private void cbbChangeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbChangeItemStateChanged
        if ("Opp".equals(cbbAddDMG.getSelectedItem().toString())) {
            if (cbbChange.getSelectedIndex() != 0) {
                lblCounter.setText("How many " + cbbChange.getSelectedItem() + " opps?");
            } else {
                lblCounter.setText("How many opps?");
            }
        }
        if ("Ally".equals(cbbAddDMG.getSelectedItem().toString())) {
            if (cbbChange.getSelectedIndex() != 0) {
                lblCounter.setText("How many " + cbbChange.getSelectedItem() + " allies?");
            } else {
                lblCounter.setText("How many allies?");
            }

        }
        if ("Opp Dice".equals(cbbAddDMG.getSelectedItem().toString())) {
            lblCounter.setText("How many opp " + cbbChange.getSelectedItem() + " dices?");
        }
        if ("Shield".equals(cbbChange.getSelectedItem().toString())) {
            txtShield.setVisible(true);
        } else {
            txtShield.setVisible(false);
        }
        savingSkill();
    }//GEN-LAST:event_cbbChangeItemStateChanged

    private void cbbAnotherAddDMGItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbAnotherAddDMGItemStateChanged
        switch (cbbAnotherAddDMG.getSelectedItem().toString()) {
            case "No Add Effect":
                txtAnotherAddDMG.setVisible(false);
                cbbAnotherOperation.setVisible(false);
            case "[VAL] if atk":
                txtAnotherAddDMG.setVisible(true);
                cbbAnotherOperation.setVisible(false);
                break;
            case "[VAL] if dfd":
                txtAnotherAddDMG.setVisible(true);
                cbbAnotherOperation.setVisible(false);
                break;
            case "[VAL] VS":
                txtAnotherAddDMG.setVisible(true);
                cbbAnotherOperation.setVisible(true);
                break;
            case "another VS":
                txtAnotherAddDMG.setVisible(false);
                cbbAnotherOperation.setVisible(true);
                break;

        }
        savingSkill();
    }//GEN-LAST:event_cbbAnotherAddDMGItemStateChanged

    private void lblTtoMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTtoMMouseClicked
        tradeTopToMid();
    }//GEN-LAST:event_lblTtoMMouseClicked

    private void lblMtoTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMtoTMouseClicked
        tradeTopToMid();
    }//GEN-LAST:event_lblMtoTMouseClicked

    private void lblMtoBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMtoBMouseClicked
        tradeBotToMid();
    }//GEN-LAST:event_lblMtoBMouseClicked

    private void lblBtoMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBtoMMouseClicked
        tradeBotToMid();
    }//GEN-LAST:event_lblBtoMMouseClicked

    private void cbbAnotherOperationItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbAnotherOperationItemStateChanged
        savingSkill();
    }//GEN-LAST:event_cbbAnotherOperationItemStateChanged

    private void txtAnotherAddDMGKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAnotherAddDMGKeyReleased

        if ("rbsy".contains(evt.getKeyChar() + "")) {
            txtN.setText(txtN.getText().toUpperCase());
        }
        if (txtN.getText().length() > 1) {
            boolean numberN1 = false;
            boolean diceN1 = false;
            for (int i = 0; i < txtN.getText().length(); i++) {
                if ("0123456789".contains(txtN.getText().subSequence(i, i + 1))) {
                    numberN1 = true;
                }
            }
            for (int i = 0; i < txtN.getText().length(); i++) {
                if ("rRyYsSbB".contains(txtN.getText().subSequence(i, i + 1))) {
                    diceN1 = true;
                }
            }
            if (numberN1 && diceN1) {
                txtN.setText(txtN.getText().substring(0, 1));
            }
            if (!numberN1 && diceN1) {
                txtN.setText(txtN.getText().substring(0, 1));
            }
        }
        savingSkill();
    }//GEN-LAST:event_txtAnotherAddDMGKeyReleased

    private void checkThunderstruckAuraItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkThunderstruckAuraItemStateChanged
        updateAurasSealsText();
    }//GEN-LAST:event_checkThunderstruckAuraItemStateChanged

    private void cbbGuildItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbGuildItemStateChanged
        switch (cbbGuild.getSelectedIndex()) {
            case 0:
                guild = "Merc";
                break;
            case 1:
                guild = "DN";
                break;
            case 2:
                guild = "Kot";
                break;
            case 3:
                guild = "Ava";
                break;
            case 4:
                guild = "Neh";
                break;
            case 5:
                guild = "Noz";
                break;
            case 6:
                guild = "Pir";
                break;
            case 7:
                guild = "RL";
                break;
            case 8:
                guild = "SH";
                break;
            case 9:
                guild = "SL";
                break;
            case 10:
                guild = "Temp";
                break;
            case 11:
                guild = "WT";
                break;
            case 12:
                guild = "Zil";
                break;
        }
        updateSupport();
    }//GEN-LAST:event_cbbGuildItemStateChanged

    private void cbbRaceItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbRaceItemStateChanged
        switch (cbbRace.getSelectedIndex()) {
            case 0:
                race = "UnkRace";
                break;
            case 1:
                race = "Beast";
                break;
            case 2:
                race = "Dais";
                break;
            case 3:
                race = "Demon";
                break;
            case 4:
                race = "Dragon";
                break;
            case 5:
                race = "Elfine";
                break;
            case 6:
                race = "Eltarite";
                break;
            case 7:
                race = "Golem";
                break;
            case 8:
                race = "Guem";
                break;
            case 9:
                race = "Homchai";
                break;
            case 10:
                race = "Human";
                break;
            case 11:
                race = "Ice Elf";
                break;
            case 12:
                race = "Solarian";
                break;
            case 13:
                race = "Undead";
                break;
        }
        if (cbbRace.getSelectedIndex() == 4) {
            checkSymb.setVisible(true);
        } else {
            checkSymb.setSelected(false);
            if (!"Symbiosis".equals(skills[0][7]) && !"Symbiosis".equals(skills[1][7]) && !"Symbiosis".equals(skills[2][7]) && !"Symbiosis".equals(skills[3][7]) && !"Symbiosis".equals(skills[4][7]) && !"Symbiosis".equals(skills[5][7]) && !"Symbiosis".equals(skills[6][7]) && !"Symbiosis".equals(skills[7][7]) && !"Symbiosis".equals(skills[8][7])) {
                checkSymb.setVisible(false);
            }
        }
    }//GEN-LAST:event_cbbRaceItemStateChanged

    private void cbbClassItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbClassItemStateChanged
        switch (cbbClass.getSelectedIndex()) {
            case 0:
                clas = "UnkClass";
                break;
            case 1:
                clas = "Bard";
                break;
            case 2:
                clas = "Berserker";
                break;
            case 3:
                clas = "Colossus";
                break;
            case 4:
                clas = "Craftsman";
                break;
            case 5:
                clas = "Mage";
                break;
            case 6:
                clas = "Marauder";
                break;
            case 7:
                clas = "Priest";
                break;
            case 8:
                clas = "Warrior";
                break;
        }
    }//GEN-LAST:event_cbbClassItemStateChanged

    private void cbbOppGuildItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbOppGuildItemStateChanged
        switch (cbbOppGuild.getSelectedIndex()) {
            case 0:
                oppguild = "Merc";
                break;
            case 1:
                oppguild = "DN";
                break;
            case 2:
                oppguild = "Kot";
                break;
            case 3:
                oppguild = "Ava";
                break;
            case 4:
                oppguild = "Neh";
                break;
            case 5:
                oppguild = "Noz";
                break;
            case 6:
                oppguild = "Pir";
                break;
            case 7:
                oppguild = "RL";
                break;
            case 8:
                oppguild = "SH";
                break;
            case 9:
                oppguild = "SL";
                break;
            case 10:
                oppguild = "Temp";
                break;
            case 11:
                oppguild = "WT";
                break;
            case 12:
                oppguild = "Zil";
                break;
        }
        updateSupport();
    }//GEN-LAST:event_cbbOppGuildItemStateChanged

    private void cbbOppClassItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbOppClassItemStateChanged
        switch (cbbOppClass.getSelectedIndex()) {
            case 0:
                oppclas = "UnkClass";
                break;
            case 1:
                oppclas = "Bard";
                break;
            case 2:
                oppclas = "Berserker";
                break;
            case 3:
                oppclas = "Colossus";
                break;
            case 4:
                oppclas = "Craftsman";
                break;
            case 5:
                oppclas = "Mage";
                break;
            case 6:
                oppclas = "Marauder";
                break;
            case 7:
                oppclas = "Priest";
                break;
            case 8:
                oppclas = "Warrior";
                break;
        }
    }//GEN-LAST:event_cbbOppClassItemStateChanged

    private void cbbOppRaceItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbOppRaceItemStateChanged
        switch (cbbOppRace.getSelectedIndex()) {
            case 0:
                opprace = "UnkRace";
                break;
            case 1:
                opprace = "Beast";
                break;
            case 2:
                opprace = "Dais";
                break;
            case 3:
                opprace = "Demon";
                break;
            case 4:
                opprace = "Dragon";
                break;
            case 5:
                opprace = "Elfine";
                break;
            case 6:
                opprace = "Eltarite";
                break;
            case 7:
                opprace = "Golem";
                break;
            case 8:
                opprace = "Guem";
                break;
            case 9:
                opprace = "Homchai";
                break;
            case 10:
                opprace = "Human";
                break;
            case 11:
                opprace = "Ice Elf";
                break;
            case 12:
                opprace = "Solarian";
                break;
            case 13:
                opprace = "Undead";
                break;
        }
    }//GEN-LAST:event_cbbOppRaceItemStateChanged

    private void rbtAtkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbtAtkItemStateChanged
        if (rbtAtk.isSelected()) {
            lblHit1.setText("How many times hit after 1st?");
            lblHit2.setText("How many times hit after 2nd?");
            lblHit3.setText("<html><center>How many times hit after 3rd?<br>No Sword Damage!</html>");
        } else {
            lblHit1.setText("How many times hit before 1st?");
            lblHit2.setText("How many times hit before 2nd?");
            lblHit3.setText("How many times hit before 3rd?");
        }
    }//GEN-LAST:event_rbtAtkItemStateChanged

    private void checkSupportItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkSupportItemStateChanged
        if (checkSupport.isSelected()) {
            lblSupport.setVisible(true);
            updateSupport();
        } else {
            lblSupport.setVisible(false);
        }
    }//GEN-LAST:event_checkSupportItemStateChanged

    private void MD4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_MD4ItemStateChanged
        if (currentSkill > 6 || currentSkill < 3) {
            showSkill();
            allPtoClear();
            pM1.setBackground(new Color(51, 87, 128));
            pickingSkill(3);
        }
        updateSidePs();
    }//GEN-LAST:event_MD4ItemStateChanged

    private void BD4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_BD4ItemStateChanged
        if (currentSkill > 3) {
            showSkill();
            allPtoClear();
            pB1.setBackground(new Color(51, 87, 128));
            pickingSkill(0);
        }
        updateSidePs();
    }//GEN-LAST:event_BD4ItemStateChanged

    private void counterStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_counterStateChanged
        skills[currentSkill][16] = counter.getValue().toString();
    }//GEN-LAST:event_counterStateChanged

    private void TD2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TD2ItemStateChanged
        if (currentSkill < 6) {
            showSkill();
            allPtoClear();
            pT1.setBackground(new Color(51, 87, 128));
            pickingSkill(6);
        }
        updateSidePs();
    }//GEN-LAST:event_TD2ItemStateChanged

    private void counter1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_counter1StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_counter1StateChanged

    private void btnHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHelpActionPerformed
        java.awt.EventQueue.invokeLater(() -> {
            new HelpScreen().setVisible(true);
        });
    }//GEN-LAST:event_btnHelpActionPerformed

    private void cbbChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbChangeActionPerformed

    }//GEN-LAST:event_cbbChangeActionPerformed

    private void txtStrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStrActionPerformed

    private void txtShieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtShieldKeyTyped
        savingSkill();
    }//GEN-LAST:event_txtShieldKeyTyped

    private void txtShieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtShieldKeyReleased

        savingSkill();
    }//GEN-LAST:event_txtShieldKeyReleased

    private void BD5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BD5KeyTyped
        if ("N".equals(evt.getKeyChar() + "") || "n".equals(evt.getKeyChar() + "")) {
            BD5.setSelectedIndex(0);
        }
        if ("R".equals(evt.getKeyChar() + "") || "r".equals(evt.getKeyChar() + "")) {
            BD5.setSelectedIndex(1);
        }
        if ("B".equals(evt.getKeyChar() + "") || "b".equals(evt.getKeyChar() + "")) {
            BD5.setSelectedIndex(2);
        }
        if ("Y".equals(evt.getKeyChar() + "") || "y".equals(evt.getKeyChar() + "")) {
            BD5.setSelectedIndex(3);
        }
        if ("S".equals(evt.getKeyChar() + "") || "s".equals(evt.getKeyChar() + "")) {
            BD5.setSelectedIndex(4);
        }
    }//GEN-LAST:event_BD5KeyTyped

    private void MD5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MD5KeyTyped
        if ("N".equals(evt.getKeyChar() + "") || "n".equals(evt.getKeyChar() + "")) {
            MD5.setSelectedIndex(0);
        }
        if ("R".equals(evt.getKeyChar() + "") || "r".equals(evt.getKeyChar() + "")) {
            MD5.setSelectedIndex(1);
        }
        if ("B".equals(evt.getKeyChar() + "") || "b".equals(evt.getKeyChar() + "")) {
            MD5.setSelectedIndex(2);
        }
        if ("Y".equals(evt.getKeyChar() + "") || "y".equals(evt.getKeyChar() + "")) {
            MD5.setSelectedIndex(3);
        }
        if ("S".equals(evt.getKeyChar() + "") || "s".equals(evt.getKeyChar() + "")) {
            MD5.setSelectedIndex(4);
        }
    }//GEN-LAST:event_MD5KeyTyped

    private void TD5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TD5KeyTyped
        if ("N".equals(evt.getKeyChar() + "") || "n".equals(evt.getKeyChar() + "")) {
            TD5.setSelectedIndex(0);
        }
        if ("R".equals(evt.getKeyChar() + "") || "r".equals(evt.getKeyChar() + "")) {
            TD5.setSelectedIndex(1);
        }
        if ("B".equals(evt.getKeyChar() + "") || "b".equals(evt.getKeyChar() + "")) {
            TD5.setSelectedIndex(2);
        }
        if ("Y".equals(evt.getKeyChar() + "") || "y".equals(evt.getKeyChar() + "")) {
            TD5.setSelectedIndex(3);
        }
        if ("S".equals(evt.getKeyChar() + "") || "s".equals(evt.getKeyChar() + "")) {
            TD5.setSelectedIndex(4);
        }
    }//GEN-LAST:event_TD5KeyTyped

    private void counterBuffDebuffStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_counterBuffDebuffStateChanged
        skills[currentSkill][21] = counterBuffDebuff.getValue().toString();
    }//GEN-LAST:event_counterBuffDebuffStateChanged

    private void checkTransItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkTransItemStateChanged
        if (checkExtra.isSelected()) {
            if (!checkTrans.isSelected()) {
                orderingDamage = new String[120][2];
            } else {
                orderingDamage = new String[36][2];
            }
        } else {
            if (!checkTrans.isSelected()) {
                orderingDamage = new String[84][2];
            } else {
                orderingDamage = new String[28][2];
            }
        }
    }//GEN-LAST:event_checkTransItemStateChanged

    private void txtYStormtxtStrKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtYStormtxtStrKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtYStormtxtStrKeyTyped

    private void txtOStormtxtStrKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOStormtxtStrKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOStormtxtStrKeyTyped

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
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new MainScreen("", "").setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup AtkDef;
    private javax.swing.JComboBox<String> BD1;
    private javax.swing.JComboBox<String> BD2;
    private javax.swing.JComboBox<String> BD3;
    private javax.swing.JComboBox<String> BD4;
    private javax.swing.JComboBox<String> BD5;
    private javax.swing.JRadioButton BlessingSeal;
    private javax.swing.JComboBox<String> D1;
    private javax.swing.JComboBox<String> D2;
    private javax.swing.JComboBox<String> D3;
    private javax.swing.JComboBox<String> D4;
    private javax.swing.JComboBox<String> D5;
    private javax.swing.JComboBox<String> D6;
    private javax.swing.JComboBox<String> D7;
    private javax.swing.JRadioButton DMGSeal;
    private javax.swing.JRadioButton DodgeSeal;
    private javax.swing.JComboBox<String> MD1;
    private javax.swing.JComboBox<String> MD2;
    private javax.swing.JComboBox<String> MD3;
    private javax.swing.JComboBox<String> MD4;
    private javax.swing.JComboBox<String> MD5;
    private javax.swing.JRadioButton NoSeal;
    private javax.swing.JRadioButton RageSeal;
    private javax.swing.JRadioButton ResilienceSeal;
    private javax.swing.ButtonGroup Seals;
    private javax.swing.JRadioButton ShieldSeal;
    private javax.swing.JRadioButton StenchSeal;
    private javax.swing.JRadioButton StrengthSeal;
    private javax.swing.JComboBox<String> TD1;
    private javax.swing.JComboBox<String> TD2;
    private javax.swing.JComboBox<String> TD3;
    private javax.swing.JComboBox<String> TD4;
    private javax.swing.JComboBox<String> TD5;
    private javax.swing.JRadioButton ThornSeal;
    private javax.swing.ButtonGroup WinLoss;
    private javax.swing.JButton btnAll;
    private javax.swing.JButton btnBMinus;
    private javax.swing.JButton btnBPlus;
    private javax.swing.JButton btnBuffs;
    private javax.swing.JButton btnCalc;
    private javax.swing.JButton btnHelp;
    private javax.swing.JButton btnLoad;
    private javax.swing.JButton btnMMinus;
    private javax.swing.JButton btnMPlus;
    private javax.swing.JButton btnOpen;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnTMinus;
    private javax.swing.JButton btnTPlus;
    private javax.swing.JComboBox<String> cbbA;
    private javax.swing.JComboBox<String> cbbAddDMG;
    private javax.swing.JComboBox<String> cbbAnotherAddDMG;
    private javax.swing.JComboBox<String> cbbAnotherOperation;
    private javax.swing.JComboBox<String> cbbAny;
    private javax.swing.JComboBox<String> cbbChange;
    private javax.swing.JComboBox<String> cbbClass;
    private javax.swing.JComboBox<String> cbbDiceChange;
    private javax.swing.JComboBox<String> cbbDiceChanged;
    private javax.swing.JComboBox<String> cbbGuild;
    private javax.swing.JComboBox<String> cbbOperation;
    private javax.swing.JComboBox<String> cbbOppClass;
    private javax.swing.JComboBox<String> cbbOppGuild;
    private javax.swing.JComboBox<String> cbbOppRace;
    private javax.swing.JComboBox<String> cbbRace;
    private javax.swing.JComboBox<String> cbbShieldbash;
    private javax.swing.JComboBox<String> cbbType;
    private javax.swing.JCheckBox checkAll;
    private javax.swing.JCheckBox checkBerserkAura;
    private javax.swing.JCheckBox checkBlizz;
    private javax.swing.JCheckBox checkBonusDMGAura;
    private javax.swing.JCheckBox checkDodgeAura;
    private javax.swing.JCheckBox checkExperimental;
    private javax.swing.JCheckBox checkExtra;
    private javax.swing.JCheckBox checkFrostbiteAura;
    private javax.swing.JCheckBox checkHeal;
    private javax.swing.JCheckBox checkHealAura;
    private javax.swing.JCheckBox checkIceAura;
    private javax.swing.JCheckBox checkMalusAura;
    private javax.swing.JCheckBox checkOpp;
    private javax.swing.JCheckBox checkRageAura;
    private javax.swing.JCheckBox checkResilienceAura;
    private javax.swing.JCheckBox checkShield;
    private javax.swing.JCheckBox checkShieldAura;
    private javax.swing.JCheckBox checkStrAura;
    private javax.swing.JCheckBox checkSupport;
    private javax.swing.JCheckBox checkSymb;
    private javax.swing.JCheckBox checkThornAura;
    private javax.swing.JCheckBox checkThunderstruckAura;
    private javax.swing.JCheckBox checkTrans;
    private javax.swing.JCheckBox checkType;
    private javax.swing.JSpinner countActiv1;
    private javax.swing.JSpinner countActiv2;
    private javax.swing.JSpinner countActiv3;
    private javax.swing.JSpinner countBlizz1;
    private javax.swing.JSpinner countBlizz2;
    private javax.swing.JSpinner countBlizz3;
    private javax.swing.JSpinner countHit1;
    private javax.swing.JSpinner countHit2;
    private javax.swing.JSpinner countHit3;
    private javax.swing.JSpinner countHitS;
    private javax.swing.JSpinner counter;
    private javax.swing.JSpinner counter1;
    private javax.swing.JSpinner counterBuffDebuff;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblAbyBOT;
    private javax.swing.JLabel lblAbyMID;
    private javax.swing.JLabel lblAbyTOP;
    private javax.swing.JLabel lblActiv;
    private javax.swing.JLabel lblActiv1;
    private javax.swing.JLabel lblActiv2;
    private javax.swing.JLabel lblActiv3;
    private javax.swing.JLabel lblAdd;
    private javax.swing.JLabel lblAddAnother;
    private javax.swing.JLabel lblAnotherWall;
    private javax.swing.JLabel lblAssuming;
    private javax.swing.JLabel lblAura;
    private javax.swing.JLabel lblAuraList;
    private javax.swing.JLabel lblB;
    private javax.swing.JLabel lblB1;
    private javax.swing.JLabel lblB2;
    private javax.swing.JLabel lblB3;
    private javax.swing.JLabel lblBlizz;
    private javax.swing.JLabel lblBlizz1;
    private javax.swing.JLabel lblBlizz2;
    private javax.swing.JLabel lblBlizz3;
    private javax.swing.JLabel lblBtoM;
    private javax.swing.JLabel lblBuff;
    private javax.swing.JLabel lblCM1;
    private javax.swing.JLabel lblCM10;
    private javax.swing.JLabel lblCM11;
    private javax.swing.JLabel lblCM12;
    private javax.swing.JLabel lblCM2;
    private javax.swing.JLabel lblCM3;
    private javax.swing.JLabel lblCM4;
    private javax.swing.JLabel lblCM5;
    private javax.swing.JLabel lblCM6;
    private javax.swing.JLabel lblCM7;
    private javax.swing.JLabel lblCM8;
    private javax.swing.JLabel lblCM9;
    private javax.swing.JLabel lblCounter;
    private javax.swing.JLabel lblCounter1;
    private javax.swing.JLabel lblDBOT1;
    private javax.swing.JLabel lblDBOT2;
    private javax.swing.JLabel lblDBOT3;
    private javax.swing.JLabel lblDBOT4;
    private javax.swing.JLabel lblDBOT5;
    private javax.swing.JLabel lblDMID1;
    private javax.swing.JLabel lblDMID2;
    private javax.swing.JLabel lblDMID3;
    private javax.swing.JLabel lblDMID4;
    private javax.swing.JLabel lblDMID5;
    private javax.swing.JLabel lblDTOP1;
    private javax.swing.JLabel lblDTOP2;
    private javax.swing.JLabel lblDTOP3;
    private javax.swing.JLabel lblDTOP4;
    private javax.swing.JLabel lblDTOP5;
    private javax.swing.JLabel lblHit;
    private javax.swing.JLabel lblHit1;
    private javax.swing.JLabel lblHit2;
    private javax.swing.JLabel lblHit3;
    private javax.swing.JLabel lblHitS;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblM;
    private javax.swing.JLabel lblM1;
    private javax.swing.JLabel lblM2;
    private javax.swing.JLabel lblM3;
    private javax.swing.JLabel lblMtoB;
    private javax.swing.JLabel lblMtoT;
    private javax.swing.JLabel lblOBerserk;
    private javax.swing.JLabel lblOBlessing;
    private javax.swing.JLabel lblOBlizzard;
    private javax.swing.JLabel lblOBulwark;
    private javax.swing.JLabel lblOCritical;
    private javax.swing.JLabel lblODMGBonus;
    private javax.swing.JLabel lblODMGMalus;
    private javax.swing.JLabel lblODodge;
    private javax.swing.JLabel lblOEclipse;
    private javax.swing.JLabel lblOFrostbite;
    private javax.swing.JLabel lblOIce;
    private javax.swing.JLabel lblOPowder;
    private javax.swing.JLabel lblORage;
    private javax.swing.JLabel lblOResilience;
    private javax.swing.JLabel lblORiposte;
    private javax.swing.JLabel lblORune;
    private javax.swing.JLabel lblOSTRBonus;
    private javax.swing.JLabel lblOSTRMalus;
    private javax.swing.JLabel lblOSTRMalus1;
    private javax.swing.JLabel lblOScarab;
    private javax.swing.JLabel lblOShield;
    private javax.swing.JLabel lblOStench;
    private javax.swing.JLabel lblOTerror;
    private javax.swing.JLabel lblOThorn;
    private javax.swing.JLabel lblOThunderstruck;
    private javax.swing.JLabel lblOpp;
    private javax.swing.JLabel lblOppSTR;
    private javax.swing.JLabel lblSTR;
    private javax.swing.JLabel lblSeal;
    private javax.swing.JLabel lblSealList;
    private javax.swing.JLabel lblShieldbash;
    private javax.swing.JLabel lblShow;
    private javax.swing.JLabel lblSpaceS;
    private javax.swing.JLabel lblSupport;
    private javax.swing.JLabel lblT;
    private javax.swing.JLabel lblT1;
    private javax.swing.JLabel lblT2;
    private javax.swing.JLabel lblT3;
    private javax.swing.JLabel lblTextSeal;
    private javax.swing.JLabel lblTo;
    private javax.swing.JLabel lblTtoM;
    private javax.swing.JLabel lblWL;
    private javax.swing.JLabel lblWall;
    private javax.swing.JLabel lblYBerserk;
    private javax.swing.JLabel lblYBlessing;
    private javax.swing.JLabel lblYBlizzard;
    private javax.swing.JLabel lblYBulwark;
    private javax.swing.JLabel lblYCritical;
    private javax.swing.JLabel lblYDMGBonus;
    private javax.swing.JLabel lblYDMGMalus;
    private javax.swing.JLabel lblYDodge;
    private javax.swing.JLabel lblYEclipse;
    private javax.swing.JLabel lblYFrostbite;
    private javax.swing.JLabel lblYIce;
    private javax.swing.JLabel lblYPowder;
    private javax.swing.JLabel lblYRage;
    private javax.swing.JLabel lblYResilience;
    private javax.swing.JLabel lblYRiposte;
    private javax.swing.JLabel lblYRune;
    private javax.swing.JLabel lblYSTRBonus;
    private javax.swing.JLabel lblYSTRMalus;
    private javax.swing.JLabel lblYSTRMalus1;
    private javax.swing.JLabel lblYScarab;
    private javax.swing.JLabel lblYShield;
    private javax.swing.JLabel lblYStench;
    private javax.swing.JLabel lblYTerror;
    private javax.swing.JLabel lblYThorn;
    private javax.swing.JLabel lblYThunderstruck;
    private javax.swing.JLabel lblYou;
    private javax.swing.JLabel lblYouAre;
    private javax.swing.JLabel lblbuffdebuff;
    private javax.swing.JPanel pActiv;
    private javax.swing.JPanel pAssume;
    private javax.swing.JPanel pAssuming;
    private javax.swing.JPanel pAuraSeal;
    private javax.swing.JPanel pAuras;
    private javax.swing.JPanel pB1;
    private javax.swing.JPanel pB2;
    private javax.swing.JPanel pB3;
    private javax.swing.JPanel pBelow;
    private javax.swing.JPanel pBlizz;
    private javax.swing.JPanel pBuffing;
    private javax.swing.JPanel pBuffs;
    private javax.swing.JPanel pChoose;
    private javax.swing.JPanel pEmpty;
    private javax.swing.JPanel pFirst;
    private javax.swing.JPanel pHeader;
    private javax.swing.JPanel pHit;
    private javax.swing.JPanel pM1;
    private javax.swing.JPanel pM2;
    private javax.swing.JPanel pM3;
    private javax.swing.JPanel pMain;
    private javax.swing.JPanel pOpp;
    private javax.swing.JPanel pOppTab;
    private javax.swing.JPanel pRoll;
    private javax.swing.JPanel pSeals;
    private javax.swing.JPanel pSecond;
    private javax.swing.JPanel pSecond1;
    private javax.swing.JPanel pSecond2;
    private javax.swing.JPanel pSecond3;
    private javax.swing.JPanel pSecond4;
    private javax.swing.JPanel pShow;
    private javax.swing.JPanel pSkill;
    private javax.swing.JPanel pT1;
    private javax.swing.JPanel pT2;
    private javax.swing.JPanel pT3;
    private javax.swing.JTabbedPane pTabs;
    private javax.swing.JPanel pThird;
    private javax.swing.JPanel pTitle;
    private javax.swing.JPanel pWSkill;
    private javax.swing.JPanel pYou;
    private javax.swing.JPanel pYouTab;
    private javax.swing.JRadioButton rbtAtk;
    private javax.swing.JRadioButton rbtDfd;
    private javax.swing.JRadioButton rbtLosing;
    private javax.swing.JRadioButton rbtWinning;
    private javax.swing.JTextField txtAddDMG;
    private javax.swing.JTextField txtAnotherAddDMG;
    private javax.swing.JTextField txtN;
    private javax.swing.JTextField txtOBerserk;
    private javax.swing.JTextField txtOBlessing;
    private javax.swing.JTextField txtOBlizzard;
    private javax.swing.JTextField txtOBulwark;
    private javax.swing.JTextField txtOCritical;
    private javax.swing.JTextField txtODMGBonus;
    private javax.swing.JTextField txtODMGMalus;
    private javax.swing.JTextField txtODodge;
    private javax.swing.JTextField txtOEclipse;
    private javax.swing.JTextField txtOFrostbite;
    private javax.swing.JTextField txtOIce;
    private javax.swing.JTextField txtOPowder;
    private javax.swing.JTextField txtORage;
    private javax.swing.JTextField txtOResilience;
    private javax.swing.JTextField txtORiposte;
    private javax.swing.JTextField txtORune;
    private javax.swing.JTextField txtOSTRBonus;
    private javax.swing.JTextField txtOSTRMalus;
    private javax.swing.JTextField txtOScarab;
    private javax.swing.JTextField txtOShield;
    private javax.swing.JTextField txtOStench;
    private javax.swing.JTextField txtOStorm;
    private javax.swing.JTextField txtOTerror;
    private javax.swing.JTextField txtOThorn;
    private javax.swing.JTextField txtOThunderstruck;
    private javax.swing.JTextField txtOppStr;
    private javax.swing.JTextField txtShield;
    private javax.swing.JTextField txtStr;
    private javax.swing.JTextField txtV;
    private javax.swing.JTextField txtYBerserk;
    private javax.swing.JTextField txtYBlessing;
    private javax.swing.JTextField txtYBlizzard;
    private javax.swing.JTextField txtYBulwark;
    private javax.swing.JTextField txtYCritical;
    private javax.swing.JTextField txtYDMGBonus;
    private javax.swing.JTextField txtYDMGMalus;
    private javax.swing.JTextField txtYDodge;
    private javax.swing.JTextField txtYEclipse;
    private javax.swing.JTextField txtYFrostbite;
    private javax.swing.JTextField txtYIce;
    private javax.swing.JTextField txtYPowder;
    private javax.swing.JTextField txtYRage;
    private javax.swing.JTextField txtYResilience;
    private javax.swing.JTextField txtYRiposte;
    private javax.swing.JTextField txtYRune;
    private javax.swing.JTextField txtYSTRBonus;
    private javax.swing.JTextField txtYSTRMalus;
    private javax.swing.JTextField txtYScarab;
    private javax.swing.JTextField txtYShield;
    private javax.swing.JTextField txtYStench;
    private javax.swing.JTextField txtYStorm;
    private javax.swing.JTextField txtYTerror;
    private javax.swing.JTextField txtYThorn;
    private javax.swing.JTextField txtYThunderstruck;
    // End of variables declaration//GEN-END:variables

    static class MyActionListener extends AbstractAction {

        JButton cliquei;

        MyActionListener(String s, JButton botone) {
            super(s);
            cliquei = botone;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(getValue(Action.NAME));
            cliquei.doClick();
        }
    }
}
