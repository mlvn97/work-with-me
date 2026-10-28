# work-with-me
Connectez vos compétences, trouvez des opportunités.

## Lancer avec Docker Compose

Copiez `.env.example` vers `.env`, remplacez le mot de passe d'exemple, puis lancez :

```sh
docker compose up --build
```

Le frontend est accessible sur `http://localhost:3000` et l'API sur
`http://localhost:8080`. Compose transmet les paramètres PostgreSQL au service
API sous les noms `DATABASE_URL`, `DATABASE_USERNAME` et `DATABASE_PASSWORD`.

En déploiement, configurez `POSTGRES_DB` et `POSTGRES_USER` comme variables
d'environnement GitHub Actions, ainsi que `POSTGRES_PASSWORD` comme secret.
Le déploiement automatique s'exécute sur le runner self-hosted de la VM ; le
runner doit pouvoir utiliser Docker Engine et Docker Compose.
