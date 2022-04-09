FROM node:14.17.4

#작업 폴더를 만들고 npm설치
WORKDIR /app

RUN npm install --silent
RUN npm install react-scripts@4 -g --silent
EXPOSE 3000
CMD ["npm", "start"]