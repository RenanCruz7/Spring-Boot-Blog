ALTER TABLE posts
ADD COLUMN user_id BIGINT,
ADD CONSTRAINT fk_posts_users
FOREIGN KEY (user_id)
REFERENCES users(id);