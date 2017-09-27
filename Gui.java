import java.awt.EventQueue;

import javax.swing.JFrame;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Gui {
	
	private JFrame frame;
	private JPanel panel_load;
	private JPanel panel_info;
	private JTextField txtLigueucd;
	private JButton btnNewButton;
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
	
	
	private Fichier test;
	
	
	
	private SpringLayout springLayout;
	private SpringLayout sl_panel_load;
	private SpringLayout sl_panel_info;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		String tab[] = new String[3];
		tab[0]="a";
		tab[1]="b";
		tab[2]="c";
		String tab2[] = new String[3];
		tab2[0]="d";
		tab2[1]="e";
		tab2[2]="f";
		String tab3[] = new String[3];
		tab3[0]="g";
		tab3[1]="h";
		tab3[2]="i";
		//Classe c1 = new Classe(tab, tab, tab);
		//Classe c2 = new Classe(tab2, tab2, tab2);
		//Lien l1 = new Lien
		
		
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
	public Gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		
		frame.setBounds(100, 100, 800, 600);
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
		
		txtLigueucd = new JTextField();
		sl_panel_load.putConstraint(SpringLayout.WEST, txtLigueucd, 10, SpringLayout.EAST, btnNewButton);
		sl_panel_load.putConstraint(SpringLayout.SOUTH, txtLigueucd, -10, SpringLayout.SOUTH, panel_load);
		sl_panel_load.putConstraint(SpringLayout.EAST, txtLigueucd, -10, SpringLayout.EAST, panel_load);
		txtLigueucd.setText("Ligue.ucd");
		sl_panel_load.putConstraint(SpringLayout.NORTH, txtLigueucd, 10, SpringLayout.NORTH, panel_load);
		panel_load.add(txtLigueucd);
		txtLigueucd.setColumns(10);
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
		
		list_classes = new JList();
		sl_panel_info.putConstraint(SpringLayout.EAST, list_classes, 246, SpringLayout.WEST, lblClasses);
		sl_panel_info.putConstraint(SpringLayout.NORTH, list_classes, 0, SpringLayout.SOUTH, lblClasses);
		sl_panel_info.putConstraint(SpringLayout.WEST, lblAttributs, 20, SpringLayout.EAST, list_classes);
		sl_panel_info.putConstraint(SpringLayout.WEST, list_classes, 10, SpringLayout.WEST, panel_info);
		sl_panel_info.putConstraint(SpringLayout.SOUTH, list_classes, -10, SpringLayout.SOUTH, panel_info);
		list_classes.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_info.add(list_classes);
		
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
		sl_panel_info.putConstraint(SpringLayout.EAST, list_attributs, 246, SpringLayout.WEST, lblAttributs);
		list_attributs.setBorder(new LineBorder(new Color(0, 0, 0)));
		sl_panel_info.putConstraint(SpringLayout.NORTH, list_attributs, 0, SpringLayout.SOUTH, lblAttributs);
		sl_panel_info.putConstraint(SpringLayout.SOUTH, list_attributs, 140, SpringLayout.SOUTH, lblAttributs);
		sl_panel_info.putConstraint(SpringLayout.NORTH, lblSousClasses, 10, SpringLayout.SOUTH, list_attributs);
		sl_panel_info.putConstraint(SpringLayout.WEST, lblMethodes, 20, SpringLayout.EAST, list_attributs);
		sl_panel_info.putConstraint(SpringLayout.WEST, list_attributs, 0, SpringLayout.WEST, lblAttributs);
		panel_info.add(list_attributs);
		
		list_sousClasses = new JList();
		list_sousClasses.setBorder(new LineBorder(new Color(0, 0, 0)));
		sl_panel_info.putConstraint(SpringLayout.NORTH, list_sousClasses, 0, SpringLayout.SOUTH, lblSousClasses);
		sl_panel_info.putConstraint(SpringLayout.SOUTH, list_sousClasses, 140, SpringLayout.SOUTH, lblSousClasses);
		sl_panel_info.putConstraint(SpringLayout.NORTH, lblDetails, 10, SpringLayout.SOUTH, list_sousClasses);
		sl_panel_info.putConstraint(SpringLayout.WEST, list_sousClasses, 0, SpringLayout.WEST, lblSousClasses);
		sl_panel_info.putConstraint(SpringLayout.EAST, list_sousClasses, 0, SpringLayout.EAST, list_attributs);
		panel_info.add(list_sousClasses);
		
		list_association = new JList();
		list_association.setBorder(new LineBorder(new Color(0, 0, 0)));
		sl_panel_info.putConstraint(SpringLayout.NORTH, list_association, 0, SpringLayout.SOUTH, lblAssociations);
		sl_panel_info.putConstraint(SpringLayout.WEST, list_association, 0, SpringLayout.WEST, lblAssociations);
		sl_panel_info.putConstraint(SpringLayout.SOUTH, list_association, 0, SpringLayout.SOUTH, list_sousClasses);
		panel_info.add(list_association);
		
		textPane_Details = new JTextPane();
		textPane_Details.setEditable(false);

		textPane_Details.setBorder(new LineBorder(new Color(0, 0, 0)));
		textPane_Details.setForeground(new Color(51, 51, 51));
		sl_panel_info.putConstraint(SpringLayout.NORTH, textPane_Details, 0, SpringLayout.SOUTH, lblDetails);
		sl_panel_info.putConstraint(SpringLayout.WEST, textPane_Details, 0, SpringLayout.WEST, lblDetails);
		sl_panel_info.putConstraint(SpringLayout.SOUTH, textPane_Details, -10, SpringLayout.SOUTH, panel_info);
		sl_panel_info.putConstraint(SpringLayout.EAST, textPane_Details, 0, SpringLayout.EAST, list_association);
		panel_info.add(textPane_Details);
		
		list_methodes = new JList();
		list_methodes.setBorder(new LineBorder(new Color(0, 0, 0)));
		sl_panel_info.putConstraint(SpringLayout.NORTH, list_methodes, 0, SpringLayout.SOUTH, lblMethodes);
		sl_panel_info.putConstraint(SpringLayout.SOUTH, list_methodes, 0, SpringLayout.SOUTH, list_attributs);
		sl_panel_info.putConstraint(SpringLayout.EAST, list_association, 0, SpringLayout.EAST, list_methodes);
		sl_panel_info.putConstraint(SpringLayout.NORTH, lblAssociations, 10, SpringLayout.SOUTH, list_methodes);
		sl_panel_info.putConstraint(SpringLayout.WEST, list_methodes, 0, SpringLayout.WEST, lblMethodes);
		sl_panel_info.putConstraint(SpringLayout.EAST, list_methodes, -10, SpringLayout.EAST, panel_info);
		panel_info.add(list_methodes);
		
		
		

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO load and parse file
				//changeFullDisplay(new Fichier());
			}
		});
		
		
		
		
		list_classes.setModel(new AbstractListModel() {
			String[] values = new String[] {"value1", "value2", "value3"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list_classes.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                  System.out.println(list_classes.getSelectedValue().toString());
                }
            }
        });
	}
	
	
	private void changeDetailDisplay(Fichier file,String association){
		//TODO
	}
	private void changeClassDisplay(Fichier file,String classe){
		//TODO
		changeDetailDisplay(file,"association");
	}
	private void changeFullDisplay(Fichier file){
		//TODO
		changeClassDisplay(file,"classe");
	}
}