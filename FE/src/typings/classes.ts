interface Classes {
  id: number;
  code?: string;
  status: string;
  timeStart: string;
  timeEnd?: string;
  process?: string;
  lessLearn?: number;
  maxStudent?: number;
  quantity?: number;
  countStudent?: number;
  courseId?: number | string;
  teacherName?: string;
  teacherId?: number | string;
  courseName?: number;
  dayOfWeek: string[];
  study?: number | string;
  totalLesson?: number;
}
interface ClassesError {
  courseId: string;
  timeStart: string;
  maxStudent: string;
  study: string;
  dayOfWeek: string;
  teacherId: string;
}

interface StudentClass {
  idStudent: number;
  codeStudent: string;
  nameStudent: string;
  points?: Point;
  timeStart?: string;
  dayOfWeek?: string[];
  lessLearn?: number;
}

interface Point {
  [string: string]: number;
}

interface AddPoint {
  codeClass: string;
  studentId: number;
  lessonId: string;
  point: number;
}

interface LessonClass {
  id: number;
  name: string;
  quantity?: number;
  learnDate?: Date;
  status?: number;
}
