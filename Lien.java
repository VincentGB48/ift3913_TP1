
public class Lien {
	public int type;
	public String nom;
	public String detail;
	public Classe classes[];
	public String cardinality[];
	public Classe container[];
	public String cardinality_containers[];
	public Classe parts[];
	public String cardinality_parts[];
	
	public Lien(Classe[] classes,String[] cardinality,String nom,String detail){
		this.type=0;
		this.classes=classes;
		this.cardinality=cardinality;
		this.nom=nom;
		this.detail=detail;
	}public Lien(TYPE type, Classe[] container,String[] cardinality_containers,
			Classe[] parts,String[] cardinality_parts,String nom,String detail){
		this.type=1;
		this.container=container;
		this.parts=parts;
		this.cardinality_containers=cardinality_containers;
		this.cardinality_parts=cardinality_parts;
		this.nom=nom;
		this.detail=detail;
	}
}