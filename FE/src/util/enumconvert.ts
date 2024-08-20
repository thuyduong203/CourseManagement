function genderType(emumType: string): string {
  switch (emumType) {
    case "MALE":
      return "Nam";
    default:
      return "Nữ";
  }
}
function roleType(emumType: string): string {
  switch (emumType) {
    case "STUDENT":
      return "Học viên";
    case "TEACHER":
      return "Giảng viên";
    case "ADMIN":
      return "Quản trị viên";
    default:
      return "Không xác định";
  }
}

function studyConvert(study: number) {
  switch (study) {
    case 1:
      return "07:15 - 09:15";
    case 2:
      return "09:20 - 11:20";
    case 3:
      return "12:00 - 14:00";
    case 4:
      return "14:10 - 16:10";
    case 5:
      return "16:20 - 18:20";
    default:
      return "18:30 - 20:30";
  }
}

function dayOfWeekConvert(text: string) {
  switch (text) {
    case "MONDAY":
      return "2";
    case "TUESDAY":
      return "3";
    case "WEDNESDAY":
      return "4";
    case "THURSDAY":
      return "5";
    case "FRIDAY":
      return "6";
    case "SATURDAY":
      return "7";
    default:
      return "CN";
  }
}

function daysPerWeekConvert(daysOfWeek: string[]) {
  const daysPerWeek: number[] = daysOfWeek.map((day) => {
    switch (day) {
      case "SUNDAY":
        return 0;
      case "MONDAY":
        return 1;
      case "TUESDAY":
        return 2;
      case "WEDNESDAY":
        return 3;
      case "THURSDAY":
        return 4;
      case "FRIDAY":
        return 5;
      case "SATURDAY":
        return 6;
      default:
        throw new Error("Invalid day of the week.");
    }
  });
  return daysPerWeek;
}

export {
  genderType,
  studyConvert,
  dayOfWeekConvert,
  daysPerWeekConvert,
  roleType,
};
