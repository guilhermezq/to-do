import "bootstrap/dist/css/bootstrap.min.css";
import { ToDoForm } from "../components/todo-form";

export default function Home() {
  return (
    <>
      <div className="container mt-3">
        <ToDoForm></ToDoForm>
      </div>
    </>
  );
}
