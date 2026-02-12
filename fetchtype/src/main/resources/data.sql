MERGE INTO department (id, name) KEY(id) VALUES (1, 'Engineering');
MERGE INTO department (id, name) KEY(id) VALUES (2, 'HR');

MERGE INTO employee (id, name, email, department_id) KEY(id)
VALUES (101, 'Arjun Kumar', 'arjun@company.com', 1);

MERGE INTO employee (id, name, email, department_id) KEY(id)
VALUES (102, 'Priya Sharma', 'priya@company.com', 1);

MERGE INTO employee (id, name, email, department_id) KEY(id)
VALUES (201, 'Rohit Verma', 'rohit@company.com', 2);