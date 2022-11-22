import React, { useRef } from "react";
import { Button, Form } from "react-bootstrap";

export const ToDoInput = (props: { addToDo: Function }) => {
  const { addToDo } = props;

  const toDoDescription = useRef<HTMLInputElement>(null);

  const formSubmit = (e: React.SyntheticEvent): void => {
    if (toDoDescription?.current?.value) {
      addToDo({ description: toDoDescription.current.value });
    }
    e.preventDefault();
  };

  return (
    <Form onSubmit={formSubmit}>
      <Form.Group controlId="toDoDescription" className="mb-3">
        <Form.Label>To-Do Entry</Form.Label>
        <Form.Control
          type="text"
          placeholder="To-Do description"
          ref={toDoDescription}
        />
      </Form.Group>

      <Button variant="primary" type="submit" className="mb-3">
        Submit
      </Button>
    </Form>
  );
};
