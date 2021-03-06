
public class Lien {
	public int type;
	public String nom;
	public String classes[];
	public String cardinality[];
	public String container[];
	public String cardinality_containers[];
	public String parts[];
	public String cardinality_parts[];
	
	//Constructeur de relation
	public Lien(String[] classes,String[] cardinality,String nom){
		this.type=0;
		this.classes=classes;
		this.cardinality=cardinality;
		this.nom=nom;
	}	
	
	//Constructeur d'aggregation
	public Lien(String[] container,String[] cardinality_containers,
			String[] parts,String[] cardinality_parts,String nom){
		this.type=1;
		this.container=container;
		this.parts=parts;
		this.cardinality_containers=cardinality_containers;
		this.cardinality_parts=cardinality_parts;
		this.nom=nom;
	}
}