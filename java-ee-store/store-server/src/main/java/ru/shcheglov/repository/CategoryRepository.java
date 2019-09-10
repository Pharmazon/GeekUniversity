package ru.shcheglov.repository;

import org.jetbrains.annotations.Nullable;
import ru.shcheglov.dto.CategoryDTO;
import ru.shcheglov.entity.Category;

/**
 * @author Alexey Shcheglov
 * @version dated 18.01.2019
 */

public interface CategoryRepository extends CommonRepository<Category> {

    Category merge(@Nullable CategoryDTO dto);

}
