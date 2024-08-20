export interface Course {
  id: number;
  code?: string;
  name: string;
  price?: number | string;
  description: string;
  lessonQuantity?: number;
  studentQuantity?: number;
  status?: string;
  timeEnd?: string;
  classQuantity?: number;
  imageUrl?: string;
}
export interface CourseError {
  name: string;
  price: string;
  description: string;
  imageUrl: string;
}
