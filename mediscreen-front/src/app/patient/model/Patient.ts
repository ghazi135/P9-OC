export interface PatientElement {
  idPatient: any;
  lastName: string;
  firstName: string;
  dateOfBirth: string;
  address: string;
  phoneNumber: number;
  sex: string;
}

export class Patient implements PatientElement {

  idPatient: any;
  lastName: string;
  firstName: string;
  dateOfBirth: string;
  address: string;
  phoneNumber: number;
  sex: string;

  constructor(idPatient: any, lastName: string, firstName: string, dateOfBirth: string, address: string, phoneNumber: number, sex: string) {
    this.idPatient = idPatient;
    this.lastName = lastName;
    this.firstName = firstName;
    this.dateOfBirth = dateOfBirth;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.sex = sex;
  }

}
