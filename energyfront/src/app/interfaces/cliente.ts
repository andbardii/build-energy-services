import { Indirizzo } from './indirizzo';
import { TipoCliente } from './tipoCliente.enum';

export interface Cliente {
  id: number;
  ragioneSociale: string;
  partitaIva: string;
  email: string;
  dataInserimento: Date;
  dataUltimoContatto: Date;
  fatturatoAnnuale: number;
  pec: string;
  emailContatto: string;
  nomeContatto: string;
  cognomeContatto: string;
  telefonoContatto: string;
  tipoCliente: TipoCliente;
  sedeLegale: Indirizzo;
  sedeOperativa: Indirizzo;
}
