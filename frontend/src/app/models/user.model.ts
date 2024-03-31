import { Car } from "./car.model";

export interface User {
  id?: number;
  firstName: string,
  lastName: string,
  email: string;
  login: string,
  // password: string,
  // birthday: Date,
  // phone: string,
  // cars: Car[]
}
