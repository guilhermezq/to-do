import { Button, Form, Table } from "react-bootstrap";
import * as Icon from "react-bootstrap-icons";
import ToDo from "../models/todo";

export const ToDoList = (props: {
  toDoList: ToDo[];
  toggleToDoActive: Function;
  fetchToDoList: Function;
  refreshToDoList: Function;
  deleteToDo: Function;
}) => {
  const { toDoList, toggleToDoActive, fetchToDoList, deleteToDo } = props;

  return (
    <Table size="xl">
      <tbody>
        {toDoList.map((toDo) => (
          <tr>
            <td>
              <Form.Check
                type="checkbox"
                checked={toDo.active}
                onChange={() => toggleToDoActive(toDo)}
              />
            </td>
            <td>
              <Form.Label>
                <p
                  style={{
                    textDecoration: toDo.active ? "line-through" : "none",
                  }}
                >
                  {toDo.description}
                </p>
              </Form.Label>
            </td>
            <td>
              <Button
                variant="danger"
                className="ms-2"
                onClick={() => deleteToDo(toDo)}
              >
                <Icon.Trash></Icon.Trash>
              </Button>
            </td>
          </tr>
        ))}
      </tbody>
    </Table>
  );
};
