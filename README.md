### Documentação Completa dos Endpoints
Usuários <br>
students:<br>
Gustavo.Lopes@example.com<br> password123<br>
pedro.chueiri@example.com<br> password123<br>
teachers:<br>
prof.a@example.com<br> 1234<br>
prof.b@example.com<br> 12345<br>
administrators:<br>
admin.1@example.com<br> 1234<br>
admin.2@example.com <br>1234<br>




#### **UsersController Endpoints**

1. **Criar Usuário (Student/Teacher/Administrator)**
   - **Método**: `POST`
   - **URL**: `/api/users`
   - **Descrição**: Cria um novo usuário de qualquer tipo (Student, Teacher ou Administrator).
   - *EXCESSÃO*: EM CASO SER STUDENT ADICIONE A KEY "class_id": long, exemplo "class_id": 2
   - **Corpo da Requisição**:
     ```json
     {
       "userType": "teacher",
       "name": "John Doe",
       "email": "john.doe@example.com",
       "password": "password123"
     }
     ```
   - **Resposta** (`HTTP 201 CREATED`):
     ```json
     {
       "id": 1,
       "name": "John Doe",
       "email": "john.doe@example.com",
       "type": "teacher",
       "active": true
     }
     ```

2. **Obter Usuário por ID**
   - **Método**: `GET`
   - **URL**: `/api/users/{userType}/{userId}`
   - **Descrição**: Retorna os detalhes de um usuário específico (Student, Teacher ou Administrator).
   - **Exemplo de URL**: `/api/users/student/1`
   - **Resposta** (`HTTP 200 OK`):
     ```json
     {
       "id": 1,
       "name": "John Doe",
       "email": "john.doe@example.com",
       "type": "student",
       "active": true
     }
     ```

3. **Atualizar Email e Senha do Usuário**
   - **Método**: `PUT`
   - **URL**: `/api/users/{userType}/{userId}`
   - **Descrição**: Atualiza o email e senha de um usuário específico.
   - **Exemplo de URL**: `/api/users/student/1`
   - **Corpo da Requisição**:
     ```json
     {
       "email": "new.email@example.com",
       "password": "newPassword123"
     }
     ```
   - **Resposta** (`HTTP 200 OK`):
     ```json
     {
       "id": 1,
       "email": "new.email@example.com",
       "type": "student"
     }
     ```

4. **Deletar Usuário (Deleção Lógica)**
   - **Método**: `DELETE`
   - **URL**: `/api/users/{userType}/{userId}`
   - **Descrição**: Marca um usuário como inativo (deleção lógica).
   - **Exemplo de URL**: `/api/users/student/1`
   - **Resposta** (`HTTP 200 OK`):
     ```json
     {
       "id": 1,
       "active": false,
       "message": "Student deletado com sucesso."
     }
     ```

5. **Obter Todos os Usuários de um Tipo**
   - **Método**: `GET`
   - **URL**: `/api/users/{userType}`
   - **Descrição**: Retorna todos os usuários do tipo especificado (Student, Teacher ou Administrator).
   - **Exemplo de URL**: `/api/users/student`
   - **Resposta** (`HTTP 200 OK`):
     ```json
     [
       {
         "id": 1,
         "name": "Student One",
         "email": "student.one@example.com",
         "type": "student",
         "active": true
       },
       {
         "id": 2,
         "name": "Student Two",
         "email": "student.two@example.com",
         "type": "student",
         "active": true
       }
     ]
     ```

6. **Autenticar Usuário (Login)**
   - **Método**: `POST`
   - **URL**: `/api/users/login`
   - **Descrição**: Autentica um usuário com base no email e senha.
   - **Corpo da Requisição**:
     ```json
     {
       "email": "john.doe@example.com",
       "password": "password123"
     }
     ```
   - **Resposta** (`HTTP 200 OK`):
     ```json
     {
       "userType": "student",
       "userId": 1
     }
     ```

#### **SchoolController Endpoints**

1. **Obter Todas as Turmas de um Professor**
   - **Método**: `GET`
   - **URL**: `/api/school/teacher/{teacherId}/classes`
   - **Descrição**: Retorna todas as turmas associadas ao professor especificado.
   - **Exemplo de URL**: `/api/school/teacher/1/classes`
   - **Resposta** (`HTTP 200 OK`):
     ```json
     [
       {
         "id": 1,
         "name": "Matemática Avançada",
         "description": "Turma de Matemática Avançada para o Ensino Médio"
       },
       {
         "id": 2,
         "name": "Física Experimental",
         "description": "Laboratório de Física para Experimentos"
       }
     ]
     ```

2. **Obter Todos os Alunos de uma Turma**
   - **Método**: `GET`
   - **URL**: `/api/school/class/{classId}/students`
   - **Descrição**: Retorna todos os alunos associados à turma especificada.
   - **Exemplo de URL**: `/api/school/class/1/students`
   - **Resposta** (`HTTP 200 OK`):
     ```json
     [
       {
         "id": 1,
         "name": "Student One",
         "email": "student.one@example.com",
         "active": true
       },
       {
         "id": 2,
         "name": "Student Two",
         "email": "student.two@example.com",
         "active": true
       }
     ]
     ```

3. **Mudar a Turma de um Estudante**
   - **Método**: `PUT`
   - **URL**: `/api/school/students/{studentId}/changeClass/{newClassId}`
   - **Descrição**: Altera a turma do estudante especificado para a nova turma.
   - **Exemplo de URL**: `/api/school/students/1/changeClass/2`
   - **Resposta** (`HTTP 200 OK`):
     ```json
     {
       "id": 1,
       "name": "Student One",
       "email": "student.one@example.com",
       "active": true
     }
     ```

4. **Mudar o Professor de uma Turma**
   - **Método**: `PUT`
   - **URL**: `/api/school/classes/{classId}/changeTeacher/{newTeacherId}`
   - **Descrição**: Altera o professor da turma especificada para o novo professor.
   - **Exemplo de URL**: `/api/school/classes/1/changeTeacher/2`
   - **Resposta** (`HTTP 200 OK`):
     ```json
     {
       "classId": 1,
       "newTeacherId": 2,
       "message": "Teacher changed successfully"
     }
     ```

### Resumo das Respostas dos Endpoints

| Método   | Endpoint                                                 | Descrição                               | Resposta (JSON)                                                                                         |
|----------|----------------------------------------------------------|-----------------------------------------|---------------------------------------------------------------------------------------------------------|
| `POST`   | `/auth/login`                                            | Login do usuário                        | `{ "userType": "student", "userId": 1 }`                                                                |
| `POST`   | `/users/{userType}`                                      | Criar um novo usuário                   | `{ "id": 1, "name": "John Doe", "email": "john.doe@example.com", "type": "student", "active": true }`   |
| `GET`    | `/users/{userType}/{userId}`                             | Obter usuário por ID                    | `{ "id": 1, "name": "John Doe", "email": "john.doe@example.com", "type": "student", "active": true }`   |
| `PUT`    | `/users/{userType}/{userId}`                             | Atualizar email e senha do usuário      | `{ "id": 1, "email": "new.email@example.com", "type": "student" }`                                      |
| `DELETE` | `/users/{userType}/{userId}`                             | Deletar usuário (lógica)                | `{ "id": 1, "active": false, "message": "Student deletado com sucesso." }`                              |
| `GET`    | `/users/{userType}`                                      | Obter todos os usuários de um tipo      | Lista de usuários (`id`, `name`, `email`, `type`, `active`)                                             |
| `GET`    | `/school/teacher/{teacherId}/classes`                    | Obter todas as turmas de um professor   | Lista de turmas (`id`, `name`, `description`)                                                           |
| `GET`    | `/school/class/{classId}/students`                       | Obter todos os alunos de uma turma      | Lista de alunos (`id`, `name`, `email`, `active`)                                                       |
| `PUT`    | `/school/students/{studentId}/changeClass/{newClassId}`  | Mudar a turma de um estudante           | Aluno atualizado (`id`, `name`, `email`, `active`)                                                      |
| `PUT`    | `/school/classes/{classId}/changeTeacher/{newTeacherId}` | Mudar o professor de uma turma          | `{ "classId": 1, "newTeacherId": 2, "message": "Teacher changed successfully" }`                        |

Se precisar de mais alguma informação ou ajuste na documentação, estarei à disposição!
