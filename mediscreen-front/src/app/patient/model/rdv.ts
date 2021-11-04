export interface RdvElement{
  idRdv: number;
  namePatient: string;
  datePriseRdv: Date;
  noteRdv: string;
}

export class Rdv implements RdvElement{
   idRdv: number;
   namePatient: string;
   datePriseRdv: Date;
   noteRdv: string;

  constructor(idRdv: number, namePatient: string, datePriseRdv: Date, noteRdv: string) {
    this.idRdv = idRdv;
    this.namePatient = namePatient;
    this.datePriseRdv = datePriseRdv;
    this.noteRdv = noteRdv;
  }
}

