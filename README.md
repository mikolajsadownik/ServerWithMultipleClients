# ServerWithMultipleClients Project

## Overview
This project, developed for the "Computer Networks and Network Programming in Java" course, focuses on creating a server capable of handling multiple clients simultaneously using TCP. The communication protocol is text-based, involving line-by-line text exchanges, including numeric values in their text representation.

## Initial Setup
- **Initial Flag**: `162979`  
  Send this flag to the server at the start of communication to initiate the process.

## Server Configuration
- **TCP Server Address**: `172.21.48.34`
- **Port**: `29799`

## Communication Protocol
Use the TCP protocol to communicate with the server following these instructions:

1. **Server Identification**: Send a single line in the format `address:port`, where `address` is your server's IP address and `port` is its port number. Your server must support multiple simultaneous client connections. Communication with each client begins by reading a line of text from the client and echoing it back, followed by these tasks for each client:
   
2. **Text Reception**: Receive a single line of text, concatenate it five times with itself, and send back the result.

3. **Sum of Numbers**: Send the sum of numbers received from all clients in their initial messages.

4. **Port Number**: Send the port number you are communicating from.

5. **Sum of Natural Numbers**: Over the next five lines, receive five natural numbers. Calculate their sum and send back the result.

6. **String Manipulation**: Receive a string, remove all occurrences of the digit `8`, and send back the result.

7. **Final Flag**: Receive the final flag from the server and record it.

## Task Notes
- Not all clients may complete the full protocol, but at least one will.
- All clients completing the protocol will return the same final flag.

