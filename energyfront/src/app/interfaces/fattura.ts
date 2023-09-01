import { StatoFattura } from "./statoFattura.enum";

export interface Fattura {
  id?:number;
  idCliente?:number;
  anno?:number;
  data?:Date;
  importo?:number;
  numero?:number;
  statoFattura?:StatoFattura;
}
