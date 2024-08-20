function closeModal(id: string): void {
  const myModal = document.getElementById(id + "Close");
  myModal?.click();
}

export { closeModal };
