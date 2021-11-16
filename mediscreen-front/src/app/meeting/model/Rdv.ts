export interface RdvElement {
  idRdv: number;
  namePatient: string;
  datePriseRdv: string;
  noteRdv: string;
}


export class Rdv implements RdvElement {
  idRdv: number;
  namePatient: string;
  datePriseRdv: string;
  noteRdv: string;

  constructor(idRdv: number, namePatient: string, datePriseRdv: string, noteRdv: string) {
    this.idRdv = idRdv;
    this.namePatient = namePatient;
    this.datePriseRdv = datePriseRdv;
    this.noteRdv = noteRdv;
  }
}
