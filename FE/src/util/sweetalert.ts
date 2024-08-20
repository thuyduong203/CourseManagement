import Swal from "sweetalert2";

async function comfirm(title: string) {
  const result = await Swal.fire({
    title: title,
    showDenyButton: true,
    confirmButtonText: "Xác nhận",
    denyButtonText: `Hủy`,
  });
  return result.isConfirmed;
}

export { comfirm };
