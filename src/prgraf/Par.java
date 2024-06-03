package prgraf;

public class Par<Atributo, Valor>{
		private Atributo atributo;
		private Valor valor;
		
		public Par(){
			this.atributo = null;
			this.valor = null;
		}
		
		public Par(Atributo a, Valor v){
			this.atributo = a;
			this.valor = v;
		}
		
		public void setAtributo (Atributo a){
			this.atributo = a;
		}
		
		public void setValor (Valor v){
			this.valor = v;
		}
		
		public Atributo getAtributo(){
			return this.atributo;
		}
		
		public Valor getValor(){
			return this.valor;
		}

		@Override
		public String toString() {
			return ("(" + this.atributo + ", " + this.valor +")");
		}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
