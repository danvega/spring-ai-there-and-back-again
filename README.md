# Spring AI: There and Back Again

The demo code from my talk at [dev2next 2026](https://www.dev2next.com/) in Lone Tree, CO.

We start with a single prompt and travel to agents, one stop at a time. Each stop adds one Spring AI idea to the same Spring Boot app.

## The stops

Every stop has its own branch, so you can start from any point in the talk.

| Stop | Branch | What it adds |
|---|---|---|
| The Shire | `01-shire` | `ChatClient`, system prompts, streaming, and structured output into a Java record |
| Bree | `02-bree` | Tools: the model can check today's date and the weather |
| Rivendell | `03-rivendell` | MCP: use a filesystem MCP server, and share our tools as an MCP server |
| Mordor | `04-mordor` | An agent: give it a goal, and it plans a route to Mount Doom |

`main` has the finished app.

```bash
git checkout 02-bree
```

## Requirements

- Java 25 or later
- An [Anthropic API key](https://console.anthropic.com/)
- Node.js, from Rivendell on (it runs the filesystem MCP server)
- [Claude Code](https://claude.com/claude-code), if you want to call the app's tools from Claude

## Run it

```bash
export SPRING_AI_ANTHROPIC_API_KEY=your-key
./mvnw spring-boot:run
```

Then open `requests.http` in IntelliJ and run the requests from top to bottom. Each one has a comment saying what to watch for.

To call the app's tools from Claude Code (Rivendell and later):

```bash
claude mcp add --transport http journey http://localhost:8080/mcp
```

## Good to know

- Getting an error on every request? Check that `SPRING_AI_ANTHROPIC_API_KEY` is set. The app still starts without it, but every call to Claude fails.
- The app uses `claude-haiku-4-5`. With Spring AI 2.0.1, Claude Opus 5.5 and Sonnet 5.5 return empty responses, because their thinking block comes back first.
- The filesystem MCP server can only read and write inside the `journeys/` folder.
- The MCP server needs `protocol: STREAMABLE` in `application.yaml`. Without it, Spring AI uses the older SSE transport.

## Keep going

- [Spring AI reference](https://docs.spring.io/spring-ai/reference)
- [Spring AI Community](https://github.com/spring-ai-community)
- [Embabel](https://github.com/embabel/embabel-agent), an agent framework for the JVM from the creator of Spring
