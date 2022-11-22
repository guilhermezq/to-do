import ToDo from "../models/todo";

export const readAll = async () => {
  const req = await fetch(`${process.env.NEXT_PUBLIC_HOST_URL}/api/v1/todo`);
  return await req.json();
};

export const deleteToDo = async (toDo: ToDo) => {
  return await fetch(`${process.env.NEXT_PUBLIC_HOST_URL}/api/v1/todo/${toDo.id}`, {
    method: "DELETE",
  });
};

export const addToDo = async (toDo: ToDo) => {
  return await fetch(`${process.env.NEXT_PUBLIC_HOST_URL}/api/v1/todo`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ description: toDo.description }),
  });
};

export const toggleToDoActive = async (toDo: ToDo) => {
  const body = JSON.stringify({ active: !toDo.active });
  await fetch(`${process.env.NEXT_PUBLIC_HOST_URL}/api/v1/todo/${toDo.id}`, {
    method: "PATCH",
    headers: {
      "Content-Type": "application/json",
    },
    body,
  });
};
