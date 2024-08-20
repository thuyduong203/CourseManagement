export interface Lesson {
  id: number;
  name: string;
  description: string;
  quantity?: number;
  status?: string;
  ordinal?: number;
  idLesson?: number;
}
export interface LessonError {
  name: string;
  description: string;
  quantity: string;
}
