use hr;
show tables;
select 
    e.first_name,
    e.last_name,
    d.department_name,
    l.city
FROM employees e
JOIN departments d ON e.department_id = d.department_id
JOIN locations l ON d.location_id = l.location_id;

SELECT 
    e.first_name AS employee_first_name,
    e.last_name AS employee_last_name,
    m.first_name AS manager_first_name,
    m.last_name AS manager_last_name
FROM employees e
LEFT JOIN employees m 
    ON e.manager_id = m.employee_id;

SELECT first_name, last_name
FROM employees
WHERE department_id = (
    SELECT department_id
    FROM employees
    WHERE first_name = 'Nancy' AND last_name = 'Greenberg'
);

SELECT 
    e.first_name AS employee_first_name,
    e.last_name AS employee_last_name,
    m.first_name AS manager_first_name,
    m.last_name AS manager_last_name,
    l.city
FROM employees e
JOIN employees m ON e.manager_id = m.employee_id
JOIN departments ed ON e.department_id = ed.department_id
JOIN departments md ON m.department_id = md.department_id
JOIN locations l ON ed.location_id = l.location_id
WHERE ed.location_id = md.location_id;

SELECT 
    e.first_name AS employee_first_name,
    e.last_name AS employee_last_name,
    m.first_name AS manager_first_name,
    m.last_name AS manager_last_name,
    e.job_id
FROM employees e
JOIN employees m 
    ON e.manager_id = m.employee_id
WHERE e.job_id = m.job_id;

