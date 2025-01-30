This is the original repository for the challenge:  
[rafaellins-itau/desafio-itau-vaga-99-junior](https://github.com/rafaellins-itau/desafio-itau-vaga-99-junior)

## About
A REST API that receives transactions and returns statistics about them.

## How to Run

I recommend using Docker to run the app.

1. Clone the repository:
   ```
   git clone git@github.com:melo-zip/desafio-itau-vaga-99-junior.git
   cd desafio-itau-vaga-99-junior/
   ```
   
2. Build the Docker image:
   ```
   docker build -t desafio-itau-vaga-99-junior .
   ```
3. Run the container (replace <host-port> with the desired port on your machine):
   ```
   docker run -p <host-port>:8080 desafio-itau-vaga-99-junior:latest
   ```