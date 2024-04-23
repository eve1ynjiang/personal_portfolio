from fastapi import FastAPI
from pydantic import BaseModel

class Project(BaseModel):
    name: str
    url: str
    
class Index(BaseModel):
    index: int

app = FastAPI()

project_list = []

@app.get('/projects')
async def root():
    return project_list

@app.post('/add_project')
async def add_project(project: Project):
    project_list.append({'name': project.name, 'url': project.url})
    return {'message': 'great success!!'}

@app.delete('/remove_project')
async def remove_project(index: Index):
    project_list.pop(index.index)
    return {'message': 'great success!!'}

