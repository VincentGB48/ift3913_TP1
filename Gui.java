import java.awt.EventQueue;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.AbstractListModel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.ListSelectionModel;


public class Gui{
	
	//instantiation des variables globales de l'interface graphique
	private  JFrame frame;
	
	private JPanel panel_load;
	private JPanel panel_info;
	private JTextField pathIn;
	private JButton btnNewButton;
	private JButton pathbtn;
	private JButton calculbtn;
	
	private JLabel lblClasses;
	private JLabel lblMethodes;
	private JLabel lblAttributs;
	private JLabel lblAssociations;
	private JLabel lblSousClasses;
	private JLabel lblDetails;
	
	private JList list_classes;
	private JList list_sousClasses;
	private JList list_attributs;
	private JList list_methodes;
	private JList list_association;
	
	private JTextPane textPane_Details;
	private JList textPane_metrique;
	private JTextPane textPane_metricdesc;
	
	private SpringLayout springLayout;
	private SpringLayout sl_panel_load;
	private SpringLayout sl_panel_info;
	
	
	private static Fichier file;

	/**
	 * Launch the application.
	 */
	public static void runGui() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui(){
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		
		frame.setBounds(100, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		panel_load = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel_load, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel_load, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel_load, 50, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel_load, -10, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(panel_load);
		
		panel_info = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel_info, 8, SpringLayout.SOUTH, panel_load);
		springLayout.putConstraint(SpringLayout.WEST, panel_info, 0, SpringLayout.WEST, panel_load);
		springLayout.putConstraint(SpringLayout.SOUTH, panel_info, -10, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel_info, 0, SpringLayout.EAST, frame.getContentPane());
		sl_panel_load = new SpringLayout();
		panel_load.setLayout(sl_panel_load);
		
		btnNewButton = new JButton("Charger fichier");
		sl_panel_load.putConstraint(SpringLayout.NORTH, btnNewButton, 10, SpringLayout.NORTH, panel_load);
		sl_panel_load.putConstraint(SpringLayout.WEST, btnNewButton, 10, SpringLayout.WEST, panel_load);
		sl_panel_load.putConstraint(SpringLayout.SOUTH, btnNewButton, -10, SpringLayout.SOUTH, panel_load);
		panel_load.add(btnNewButton);
		
		pathIn = new JTextField();
		sl_panel_load.putConstraint(SpringLayout.WEST, pathIn, 10, SpringLayout.EAST, btnNewButton);
		sl_panel_load.putConstraint(SpringLayout.SOUTH, pathIn, -10, SpringLayout.SOUTH, panel_load);
		sl_panel_load.putConstraint(SpringLayout.EAST, pathIn, 540, SpringLayout.EAST, btnNewButton);
		sl_panel_load.putConstraint(SpringLayout.NORTH, pathIn, 10, SpringLayout.NORTH, panel_load);
		panel_load.add(pathIn);
		pathIn.setColumns(10);

		pathbtn = new JButton("  ...  ");
		sl_panel_load.putConstraint(SpringLayout.NORTH, pathbtn, 0, SpringLayout.NORTH, btnNewButton);
		sl_panel_load.putConstraint(SpringLayout.WEST, pathbtn, 6, SpringLayout.EAST, pathIn);
		sl_panel_load.putConstraint(SpringLayout.SOUTH, pathbtn, 0, SpringLayout.SOUTH, btnNewButton);
		sl_panel_load.putConstraint(SpringLayout.EAST, pathbtn, 95, SpringLayout.EAST, pathIn);
		panel_load.add(pathbtn);
		frame.getContentPane().add(panel_info);
		sl_panel_info = new SpringLayout();
		panel_info.setLayout(sl_panel_info);
		
		lblClasses = new JLabel("Classes");
		sl_panel_info.putConstraint(SpringLayout.NORTH, lblClasses, 10, SpringLayout.NORTH, panel_info);
		sl_panel_info.putConstraint(SpringLayout.WEST, lblClasses, 10, SpringLayout.WEST, panel_info);
		panel_info.add(lblClasses);
		
		lblAttributs = new JLabel("Attributs");
		sl_panel_info.putConstraint(SpringLayout.SOUTH, lblAttributs, 0, SpringLayout.SOUTH, lblClasses);
		panel_info.add(lblAttributs);
		
		lblMethodes = new JLabel("Methodes");
		sl_panel_info.putConstraint(SpringLayout.NORTH, lblMethodes, 10, SpringLayout.NORTH, panel_info);
		panel_info.add(lblMethodes);
		
		lblSousClasses = new JLabel("Sous-classes");
		sl_panel_info.putConstraint(SpringLayout.WEST, lblSousClasses, 0, SpringLayout.WEST, lblAttributs);
		panel_info.add(lblSousClasses);
		
		lblAssociations = new JLabel("Association/Aggregations");
		sl_panel_info.putConstraint(SpringLayout.WEST, lblAssociations, 0, SpringLayout.WEST, lblMethodes);
		panel_info.add(lblAssociations);
		
		lblDetails = new JLabel("Details");
		sl_panel_info.putConstraint(SpringLayout.WEST, lblDetails, 0, SpringLayout.WEST, lblAttributs);
		panel_info.add(lblDetails);
		
		list_attributs = new JList();
		list_attributs.setEnabled(false);
		list_attributs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sl_panel_info.putConstraint(SpringLayout.EAST, list_attributs, 246, SpringLayout.WEST, lblAttributs);
		list_attributs.setBorder(new LineBorder(new Color(0, 0, 0)));
		sl_panel_info.putConstraint(SpringLayout.NORTH, list_attributs, 0, SpringLayout.SOUTH, lblAttributs);
		sl_panel_info.putConstraint(SpringLayout.SOUTH, list_attributs, 140, SpringLayout.SOUTH, lblAttributs);
		sl_panel_info.putConstraint(SpringLayout.WEST, list_attributs, 0, SpringLayout.WEST, lblAttributs);
		panel_info.add(list_attributs);
		
		list_sousClasses = new JList();
		list_sousClasses.setEnabled(false);
		list_sousClasses.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_sousClasses.setBorder(new LineBorder(new Color(0, 0, 0)));
		sl_panel_info.putConstraint(SpringLayout.NORTH, list_sousClasses, 0, SpringLayout.SOUTH, lblSousClasses);
		sl_panel_info.putConstraint(SpringLayout.SOUTH, list_sousClasses, 140, SpringLayout.SOUTH, lblSousClasses);
		sl_panel_info.putConstraint(SpringLayout.WEST, list_sousClasses, 0, SpringLayout.WEST, lblSousClasses);
		panel_info.add(list_sousClasses);
		
		list_association = new JList();
		list_association.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_association.setBorder(new LineBorder(new Color(0, 0, 0)));
		sl_panel_info.putConstraint(SpringLayout.NORTH, list_association, 0, SpringLayout.SOUTH, lblAssociations);
		sl_panel_info.putConstraint(SpringLayout.WEST, list_association, 0, SpringLayout.WEST, lblAssociations);
		sl_panel_info.putConstraint(SpringLayout.SOUTH, list_association, 0, SpringLayout.SOUTH, list_sousClasses);
		panel_info.add(list_association);
		
		list_methodes = new JList();
		list_methodes.setEnabled(false);
		list_methodes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list_methodes.setBorder(new LineBorder(new Color(0, 0, 0)));
		sl_panel_info.putConstraint(SpringLayout.NORTH, list_methodes, 0, SpringLayout.SOUTH, lblMethodes);
		sl_panel_info.putConstraint(SpringLayout.SOUTH, list_methodes, 0, SpringLayout.SOUTH, list_attributs);
		sl_panel_info.putConstraint(SpringLayout.EAST, list_association, 0, SpringLayout.EAST, list_methodes);
		sl_panel_info.putConstraint(SpringLayout.WEST, list_methodes, 0, SpringLayout.WEST, lblMethodes);
		sl_panel_info.putConstraint(SpringLayout.EAST, list_methodes, -10, SpringLayout.EAST, panel_info);
		panel_info.add(list_methodes);
		
		textPane_Details = new JTextPane();
		textPane_Details.setEditable(false);
		
				textPane_Details.setBorder(new LineBorder(new Color(0, 0, 0)));
				textPane_Details.setForeground(new Color(51, 51, 51));
				sl_panel_info.putConstraint(SpringLayout.NORTH, textPane_Details, 0, SpringLayout.SOUTH, lblDetails);
				sl_panel_info.putConstraint(SpringLayout.WEST, textPane_Details, 0, SpringLayout.WEST, lblDetails);
				sl_panel_info.putConstraint(SpringLayout.SOUTH, textPane_Details, -10, SpringLayout.SOUTH, panel_info);
				sl_panel_info.putConstraint(SpringLayout.EAST, textPane_Details, 0, SpringLayout.EAST, list_association);
				panel_info.add(textPane_Details);
				
				
				JScrollPane scrollPane_detail = new JScrollPane(textPane_Details);
				sl_panel_info.putConstraint(SpringLayout.NORTH, scrollPane_detail, 0, SpringLayout.SOUTH, lblDetails);
				sl_panel_info.putConstraint(SpringLayout.WEST, scrollPane_detail, 0, SpringLayout.WEST, lblDetails);
				sl_panel_info.putConstraint(SpringLayout.SOUTH, scrollPane_detail, -10, SpringLayout.SOUTH, panel_info);
				panel_info.add(scrollPane_detail);
		
		list_classes = new JList();
		list_classes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sl_panel_info.putConstraint(SpringLayout.EAST, list_classes, 246, SpringLayout.WEST, lblClasses);
		sl_panel_info.putConstraint(SpringLayout.NORTH, list_classes, 0, SpringLayout.SOUTH, lblClasses);
		sl_panel_info.putConstraint(SpringLayout.WEST, list_classes, 10, SpringLayout.WEST, panel_info);
		sl_panel_info.putConstraint(SpringLayout.SOUTH, list_classes, -10, SpringLayout.SOUTH, panel_info);
		list_classes.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_info.add(list_classes);
		JScrollPane scrollPane_classes = new JScrollPane(list_classes);
		sl_panel_info.putConstraint(SpringLayout.EAST, scrollPane_classes, 246, SpringLayout.WEST, lblClasses);
		sl_panel_info.putConstraint(SpringLayout.WEST, lblAttributs, 20, SpringLayout.EAST, scrollPane_classes);
		sl_panel_info.putConstraint(SpringLayout.NORTH, scrollPane_classes, 0, SpringLayout.SOUTH, lblClasses);
		sl_panel_info.putConstraint(SpringLayout.WEST, scrollPane_classes, 0, SpringLayout.WEST, panel_info);
		sl_panel_info.putConstraint(SpringLayout.SOUTH, scrollPane_classes, -10, SpringLayout.SOUTH, panel_info);
		panel_info.add(scrollPane_classes);
		
		String current = System.getProperty("user.dir");

		pathIn.setText(current+"/");
		
		calculbtn = new JButton("Caluler Metrique");
		sl_panel_load.putConstraint(SpringLayout.NORTH, calculbtn, 0, SpringLayout.NORTH, btnNewButton);
		sl_panel_load.putConstraint(SpringLayout.WEST, calculbtn, 20, SpringLayout.EAST, pathbtn);
		sl_panel_load.putConstraint(SpringLayout.SOUTH, calculbtn, 0, SpringLayout.SOUTH, btnNewButton);
		sl_panel_load.putConstraint(SpringLayout.EAST, calculbtn, -10, SpringLayout.EAST, panel_load);
		panel_load.add(calculbtn);
		
		
		
		
		JScrollPane scrollPane_attributs = new JScrollPane(list_attributs);
		sl_panel_info.putConstraint(SpringLayout.SOUTH, scrollPane_attributs, 140, SpringLayout.SOUTH, lblAttributs);
		sl_panel_info.putConstraint(SpringLayout.EAST, list_sousClasses, 0, SpringLayout.EAST, scrollPane_attributs);
		sl_panel_info.putConstraint(SpringLayout.NORTH, lblSousClasses, 10, SpringLayout.SOUTH, scrollPane_attributs);
		sl_panel_info.putConstraint(SpringLayout.NORTH, scrollPane_attributs, 0, SpringLayout.SOUTH, lblAttributs);
		sl_panel_info.putConstraint(SpringLayout.WEST, scrollPane_attributs, 0, SpringLayout.WEST, lblAttributs);
		sl_panel_info.putConstraint(SpringLayout.WEST, lblMethodes, 20, SpringLayout.EAST, scrollPane_attributs);
		sl_panel_info.putConstraint(SpringLayout.EAST, scrollPane_attributs, 246, SpringLayout.WEST, lblAttributs);
		panel_info.add(scrollPane_attributs);
		JScrollPane scrollPane_methodes = new JScrollPane(list_methodes);
		sl_panel_info.putConstraint(SpringLayout.NORTH, scrollPane_methodes, 0, SpringLayout.SOUTH, lblMethodes);
		sl_panel_info.putConstraint(SpringLayout.EAST, scrollPane_methodes, 246, SpringLayout.WEST, lblMethodes);
		sl_panel_info.putConstraint(SpringLayout.NORTH, lblAssociations, 10, SpringLayout.SOUTH, scrollPane_methodes);
		sl_panel_info.putConstraint(SpringLayout.WEST, scrollPane_methodes, 0, SpringLayout.WEST, lblMethodes);
		sl_panel_info.putConstraint(SpringLayout.SOUTH, scrollPane_methodes, 140, SpringLayout.SOUTH, lblMethodes);
		panel_info.add(scrollPane_methodes);
		JScrollPane scrollPane_ssClasses = new JScrollPane(list_sousClasses);
		sl_panel_info.putConstraint(SpringLayout.EAST, scrollPane_ssClasses, 246, SpringLayout.WEST, lblSousClasses);
		sl_panel_info.putConstraint(SpringLayout.NORTH, lblDetails, 10, SpringLayout.SOUTH, scrollPane_ssClasses);
		sl_panel_info.putConstraint(SpringLayout.NORTH, scrollPane_ssClasses, 0, SpringLayout.SOUTH, lblSousClasses);
		sl_panel_info.putConstraint(SpringLayout.WEST, scrollPane_ssClasses, 0, SpringLayout.WEST, lblSousClasses);
		sl_panel_info.putConstraint(SpringLayout.SOUTH, scrollPane_ssClasses, 140, SpringLayout.SOUTH, lblSousClasses);
		panel_info.add(scrollPane_ssClasses);
		JScrollPane scrollPane_association = new JScrollPane(list_association);
		sl_panel_info.putConstraint(SpringLayout.EAST, scrollPane_detail, 0, SpringLayout.EAST, scrollPane_association);
		sl_panel_info.putConstraint(SpringLayout.NORTH, scrollPane_association, 0, SpringLayout.SOUTH, lblAssociations);
		sl_panel_info.putConstraint(SpringLayout.WEST, scrollPane_association, 0, SpringLayout.WEST, lblAssociations);
		sl_panel_info.putConstraint(SpringLayout.SOUTH, scrollPane_association, 140, SpringLayout.SOUTH, lblAssociations);
		sl_panel_info.putConstraint(SpringLayout.EAST, scrollPane_association, 246, SpringLayout.WEST, lblAssociations);
		panel_info.add(scrollPane_association);
		
		textPane_metrique = new JList();
		sl_panel_info.putConstraint(SpringLayout.SOUTH, textPane_metrique, 10, SpringLayout.SOUTH, scrollPane_association);
		textPane_metrique.setBorder(new LineBorder(new Color(0, 0, 0)));
		sl_panel_info.putConstraint(SpringLayout.EAST, textPane_metrique, -10, SpringLayout.EAST, panel_info);
		panel_info.add(textPane_metrique);
		
		JLabel lblmetrique = new JLabel("Metriques");
		sl_panel_info.putConstraint(SpringLayout.NORTH, textPane_metrique, 0, SpringLayout.SOUTH, lblmetrique);
		sl_panel_info.putConstraint(SpringLayout.WEST, textPane_metrique, 0, SpringLayout.WEST, lblmetrique);
		sl_panel_info.putConstraint(SpringLayout.NORTH, lblmetrique, 0, SpringLayout.NORTH, lblClasses);
		sl_panel_info.putConstraint(SpringLayout.WEST, lblmetrique, 20, SpringLayout.EAST, scrollPane_methodes);
		panel_info.add(lblmetrique);
		
		textPane_metricdesc = new JTextPane();
		textPane_metricdesc.setBorder(new LineBorder(new Color(0, 0, 0)));
		JScrollPane scrollPane_metricdesc = new JScrollPane(textPane_metricdesc);

		sl_panel_info.putConstraint(SpringLayout.NORTH, scrollPane_metricdesc, 5, SpringLayout.SOUTH, textPane_metrique);
		sl_panel_info.putConstraint(SpringLayout.WEST, scrollPane_metricdesc, 0, SpringLayout.WEST, textPane_metrique);
		sl_panel_info.putConstraint(SpringLayout.SOUTH, scrollPane_metricdesc, 0, SpringLayout.SOUTH, scrollPane_detail);
		sl_panel_info.putConstraint(SpringLayout.EAST, scrollPane_metricdesc, 0, SpringLayout.EAST, textPane_metrique);
		panel_info.add(scrollPane_metricdesc);
		
		// Action du bouton *Charger fichier*
		//prend le chemin dans la boite de text 'pathIn' et l'envoie a TP1 pour parser le fichier
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(pathIn.getText().split(System.getProperty("user.dir")).length);
				System.out.println("in : "+pathIn.getText());
				
				changeFullDisplay(TP1.loadFile(pathIn.getText()));
			}
		});

		// Action du bouton *...*
		//Active le navigateur de fichier
		//prend le chemin en resultat et l'envoie a TP1 pour parser le fichier 
		pathbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Handle open button action.

				final JFileChooser fileChooser = new JFileChooser();
				int result = fileChooser.showOpenDialog(panel_load);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					pathIn.setText(selectedFile.getAbsolutePath());
					System.out.println(selectedFile.getAbsolutePath());
					changeFullDisplay(TP1.loadFile(selectedFile.getAbsolutePath()));

				}
			}
		});
		

		//bouton creant le fichier cvs
		calculbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					TP1.newCVS(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		// Action de la liste de classe lors d'une selection
		//Active le changement d'affichage concordant avec la classe selectionner
		list_classes.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {

                	int classeIndex = list_classes.getSelectedIndex();
                	if (classeIndex==-1)classeIndex=0;
                	changeClassDisplay(file,classeIndex,null);
                }
            }
        });
		

		// Action de la liste de liaison lors d'une selection
		//Active le changement d'affichage concordant avec la liaison selectionner
		list_association.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                	int classeIndex = list_classes.getSelectedIndex();
                	int associationIndex = list_association.getSelectedIndex();
                	if (classeIndex==-1)classeIndex=0;
                	if (associationIndex==-1)associationIndex=0;
                	changeDetailDisplay(file,classeIndex,associationIndex,null);
                }
            }
        });
		
		//Change l'affichage de la description du metric selectionnee
		textPane_metrique.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent arg0) {
            	String textval = "";
                if (!arg0.getValueIsAdjusting()) {
                	int Index = textPane_metrique.getSelectedIndex();
                	if (Index==-1)Index=0;
                	switch(Index){
                	case 0 : textval="ANA(x) Nombre moyen d’arguments des méthodes locales pour la classe x.";break;
                	case 1 : textval="NOM(x): Nombre  de  méthodes  locales/héritées  de  la  classe x.  Dans  le  cas  où  une méthode est héritée et redéfinie localement (même nom, même ordre et types des arguments et même type de retour), elle ne compte qu’une fois.";break;
                	case 2 : textval="NOA(x): Nombre d’attributs locaux/hérités de la classe x";break;
                	case 3 : textval="ITC(x): Nombre de fois où d’autres classes du diagramme apparaissent comme types des arguments des méthodes de x.";break;
                	case 4 : textval="ETC(x): Nombre de fois où x apparaît comme type des arguments dans les méthodes des autres classes du diagramme.";break;
                	case 5 : textval="CAC(x): Nombre d’associations (incluant les agrégations) locales/héritées auxquelles participe une classe x.";break;
                	case 6 : textval="DIT(x): Taille du chemin le plus long reliant une classe x à une classe racine dans le graphe d’héritage";break;
                	case 7 : textval="CLD(x): Taille du chemin le plus long reliant une classe x à une classe feuille dans le graphe d’héritage.";break;
                	case 8 : textval="NOC(x): Nombre de sous-classes directes de x.";break;
                	case 9 : textval="NOD(x) : Nombre de sous-classes directes et indirectes de x.";break;
                	}
                	textPane_metricdesc.setText(textval);
                }
            }
        });
	}
	
	//Change l'affichage de la boite detail selon le string en parametre
	private void changeDetailString(String details){
		textPane_Details.setText(details);
	}
	
	//change la liste de metric correspondant a la classe
	private void changeMetricString(String metrics){
		changeList(textPane_metrique,metrics.split(","));
	}
	
	//prepare l'affichage de la boite detail dependament que ce soit une relation ou une aggregation
	//qui fut selectionnee
	private void changeDetailDisplay(Fichier file,int classeIndex, int lienIndex,String error){
		if(error == null&&file.liens.length>0&&file.classes[classeIndex].indexLiens.length>0){
		Lien l = file.liens[file.classes[classeIndex].indexLiens[lienIndex]];
		String detailstring="";
		if (l.type==0){
			detailstring+="Relation "+l.nom+"\n\tROLES";
			int nbRoles = l.classes.length;
			for(int i=0;i<nbRoles;i++){
				detailstring += "\n\t\t" + l.classes[i] +" "+ l.cardinality[i];
			}
			
		}
		else if (l.type==1){
			detailstring+="AGGREGATION "+l.nom+"\n\tCONTAINER";
			int nbContainer = l.container.length;
			for(int i=0;i<nbContainer;i++){
				detailstring += "\n\t\t" + l.container[i] +" "+ l.cardinality_containers[i];
			}
			detailstring+="\n\tPARTS";
			int nbParts = l.parts.length;
			for(int i=0;i<nbParts;i++){
				detailstring += "\n\t\t" + l.parts[i] +" "+ l.cardinality_parts[i];
			}
		}
		changeDetailString(detailstring);
		}
		else changeDetailString(error);
	}
	
	//Change l'affichage selon la classe selectionner
	private void changeClassDisplay(Fichier file, int classeindex,String error){
		if(error == null){
		Classe c = file.classes[classeindex];
		int attlength = c.attributs.length;
		int metlength = c.methodes.length;
		int ssclength = c.sousClasses.length;
		int linlength = c.indexLiens.length;

		final String att[] = new String[attlength];
		final String met[] = new String[metlength];
		final String ssc[] = new String[ssclength];
		final String lin[] = new String[linlength];
		for (int i = 0 ; i<attlength;i++){
			att[i]=c.attributs[i].nom+" : "+c.attributs[i].type;
		}
		for (int i = 0 ; i<metlength;i++){
			String temp ="";
			for (int j = 0; j<c.methodes[i].arg.length; j++)
			{
				temp+=c.methodes[i].arg[j].nom+" : "+c.methodes[i].arg[j].type+" ";
			}
			met[i]=c.methodes[i].nom+" ( "+temp+" ) "+c.methodes[i].type;
			//System.out.println(c.methodes[i].nom);
			//System.out.println(met[i]);
			//System.out.println(temp);
			//System.out.println(c.methodes[i].type);
		}
		for (int i = 0 ; i<ssclength;i++){
			ssc[i]=c.sousClasses[i].nom;
		}
		for (int i = 0 ; i<linlength;i++){
			lin[i]=file.liens[c.indexLiens[i]].nom;
		}
		

		changeList(list_attributs,att);
		changeList(list_methodes,met);
		changeList(list_sousClasses,ssc);
		changeList(list_association,lin);
		list_association.setSelectedIndex(0);
		changeMetricString(TP1.getMetric(file,c));
		changeDetailDisplay(file,classeindex,0,null);}
		else changeDetailDisplay(file,classeindex,0,error);
	}
	
	
	
	
	
	//change l'affichage complet apres le chargement d'un nouveau fichier
	private void changeFullDisplay(Fichier file){
		this.file = file;
		if(file.valide==true){
			int length = file.classes.length;
			final String classeOut[] = new String[length];
			for (int i = 0 ; i<length;i++){
				classeOut[i]=file.classes[i].nom;
			}
			changeList(list_classes,classeOut);
			list_classes.setSelectedIndex(0);
			changeClassDisplay(file,0,null);
		}
		else {
			changeClassDisplay(file,0,"Erreur : \""+pathIn.getText()+"\". "+file.modele);
			}
	}
	
	//fonction general qui change le contenu d'un JList present dans les variables globals.
	private void changeList(JList list, final String[] values){
		list.setModel(new AbstractListModel() {
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
	}
}
