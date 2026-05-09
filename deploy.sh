#!/bin/bash
set -e

VERSION=$1

if [ -z "$VERSION" ]; then
  echo "❌ Usage: ./deploy.sh v1.0.2"
  exit 1
fi

echo "🚀 Deploying version $VERSION..."

kubectl set image deployment/backend \
  backend=ghcr.io/marcvog/experiment-backend:$VERSION

kubectl set image deployment/frontend \
  frontend=ghcr.io/marcvog/experiment-frontend:$VERSION

echo "⏳ Waiting for rollout..."

kubectl rollout status deployment/backend
kubectl rollout status deployment/frontend

echo "✅ Deployment successful!"
