export interface RepportElement{
 serialVersionUID: number;
 lastName: string;
 firstName: string;
 sex: string;
 age: number;
 status: string;
}

export class Repport implements RepportElement{
   serialVersionUID: number;
   lastName: string;
   firstName: string;
   sex: string;
   age: number;
   status: string;


  constructor(serialVersionUID: number, lastName: string, firstName: string, sex: string, age: number, status: string) {
    this.serialVersionUID = serialVersionUID;
    this.lastName = lastName;
    this.firstName = firstName;
    this.sex = sex;
    this.age = age;
    this.status = status;
  }
}

