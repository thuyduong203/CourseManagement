export interface Teacher {
  id: number;
  code: string;
  name: string;
  birthDate: string;
  email: string;
  phone: string;
  gender: string;
  address: string;
  status: string;
  totalClass?: number;
}
export interface TeacherEror {
  name: string;
  birthDate: string;
  email: string;
  phone: string;
  gender: string;
  address: string;
}
