import { useEffect, useState } from "react";
import { default as ToDo, default as toDo } from "../models/todo";
import { ToDoInput } from "./todo-input";
import { ToDoList } from "./todo-list";
import * as toDoApi from "../apis/ToDoApi";

export const ToDoForm = () => {
  const [toDoList, setToDoList] = useState<ToDo[]>([]);

  useEffect(() => {
    fetchToDoList();
  }, []);

  const refreshToDoList = () => {
    setToDoList([...toDoList]);
  };

  const fetchToDoList = async () => {
    const list = await toDoApi.readAll();
    setToDoList(list);
  };

  const deleteToDo = async (toDo: ToDo) => {
    await toDoApi.deleteToDo(toDo);
    fetchToDoList();
  };

  const toggleToDoActive = async (toDo: ToDo) => {
    await toDoApi.toggleToDoActive(toDo);
    fetchToDoList();
  };

  const addToDo = async (toDo: ToDo) => {
    await toDoApi.addToDo(toDo);
    fetchToDoList();
  };

  return (
    <>
      <ToDoInput addToDo={addToDo}></ToDoInput>
      <ToDoList
        toDoList={toDoList}
        toggleToDoActive={toggleToDoActive}
        fetchToDoList={fetchToDoList}
        refreshToDoList={refreshToDoList}
        deleteToDo={deleteToDo}
      ></ToDoList>
    </>
  );
};
