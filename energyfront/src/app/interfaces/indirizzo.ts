import { Comune } from './comune';

export interface Indirizzo {
  id: number;
  via: string;
  civico: number;
  localita: string;
  cap: number;
  comune: Comune;
}
